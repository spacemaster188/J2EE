package by.gsu.epamlab.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.request.async.DeferredResult;

import by.gsu.epamlab.entity.MessageEntity;
import by.gsu.epamlab.entity.UserEntity;
import by.gsu.epamlab.service.InMemoryChatRepository;
import by.gsu.epamlab.service.MessageManager;
import by.gsu.epamlab.service.UserManager;
import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.utils.StringUtils;

@Controller
public class MainController {
	@Autowired
	private MessageManager messageManager;
	
	@Autowired
	private UserManager userManager;
	/** Message service messages List*/
	private List<MessageEntity> messages;
	/** Message service chatRequests*/
	private final Map<DeferredResult<List<MessageEntity>>, Integer> chatRequests =
			new ConcurrentHashMap<DeferredResult<List<MessageEntity>>, Integer>();
	
	private ModelAndView modelAndView;
	
	private UserEntity usr;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String redirectLogin() {
		return Constants.LOGIN;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String redirectRegister() {
		return Constants.REGISTRATION;
	}
		        
	@RequestMapping(value = "/listenMessageLong", method = RequestMethod.POST)
	@ResponseBody
	public DeferredResult<List<MessageEntity>> serviceWaitingClientMessages(@RequestParam ("lastIncomingMessageLong") int messageIndex) {
		System.out.println("Incoming index: " + messageIndex);
		final DeferredResult<List<MessageEntity>> chatRequest = new DeferredResult<List<MessageEntity>>(null, Collections.emptyList());
		this.chatRequests.put(chatRequest, messageIndex);

		chatRequest.onCompletion(new Runnable() {
			@Override
			public void run() {
				chatRequests.remove(chatRequest);
			}
		});

		List<MessageEntity> messagesTmpLst = getMessages(messageIndex);
		if (!messagesTmpLst.isEmpty()) {
			chatRequest.setResult(messagesTmpLst);
		}

		return chatRequest;
	}

	@RequestMapping(value = "/addMsgLong", method = RequestMethod.POST)
	@ResponseBody
	public void postMessage(@RequestParam ("json") String jsonStr) {
		System.out.println(jsonStr);
		MessageEntity msg = new MessageEntity();
		msg.setMsg(jsonStr);
		messageManager.addMessage(msg,usr);
		addMessage(msg);
		
		for (Entry<DeferredResult<List<MessageEntity>>, Integer> entry : this.chatRequests.entrySet()) {
			List<MessageEntity> messagesTmp = getMessages(entry.getValue());
			entry.getKey().setResult(messagesTmp);
		}
	}
	
	@RequestMapping(value = "/listenMessagesShort", method = RequestMethod.POST)
	@ResponseBody
    public List<MessageEntity> getUpdatedMessages() {
        return messageManager.getAllMessages();
    }
	
	@RequestMapping(value = "/addMsgShort", method = RequestMethod.POST)
	@ResponseBody
	public List<MessageEntity> saveNewMessage(@RequestParam ("json") String jsonStr) {
		MessageEntity msg = new MessageEntity();
		msg.setMsg(jsonStr);
		messageManager.addMessage(msg,usr);
	    return messageManager.getAllMessages();
	}
	
	@RequestMapping(value = "/getRegister", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("users") UserEntity user, BindingResult result) {
		if(StringUtils.checked(user.getLogin()) && StringUtils.checked(user.getPassword()) && !userManager.hasLogin(user)){
			userManager.addUser(user);
			usr = user;
			modelAndView.addObject("usr", usr);
		}

		return "redirect:/chat";
	}
	
	@RequestMapping(value = "/getLogin", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute("users") UserEntity user, BindingResult result) {
		if(userManager.hasLogin(user)){
			usr = userManager.getFullyUser(user);
			modelAndView.addObject("usr", usr);

			return "redirect:/chat";
		} else {
			return Constants.LOGIN;
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNewMessage(@RequestParam("msg") String message) {
		MessageEntity msg = new MessageEntity();
		msg.setMsg(message);
		System.out.println(message);
		messageManager.addMessage(msg,usr);		
		return "redirect:/chat";
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView proceedChat(ModelMap map) {
		modelAndView.addObject("messages", new MessageEntity());
		modelAndView.addObject("users", new UserEntity());
		if(usr!=null){
			//do smth	
		}
		
		modelAndView.setViewName(Constants.CHATROOM);
		return modelAndView;
	}
	 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() 
	{
		modelAndView.clear();
		usr=null;
		return "redirect:/login";
	}
	
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public ModelAndView getModelAndView() {
		return modelAndView;
	}

	public void setModelAndView(ModelAndView modelAndView) {
		this.modelAndView = modelAndView;
	}

	public MainController() {
		this.messages = InMemoryChatRepository.messages;
		this.modelAndView = new ModelAndView();
		this.usr = new UserEntity();
	}
	/** Message service get messages*/
	public List<MessageEntity> getMessages(int index) {
		if (this.messages.isEmpty()) {
			return Collections.<MessageEntity> emptyList();
		}
        Assert.isTrue((index >= 0) && (index <= this.messages.size()), "Invalid message index");
		return this.messages.subList(index, this.messages.size());
	}
	/** Message service add new message*/
	public void addMessage(MessageEntity message) {
		this.messages.add(message);
	}
	
}
