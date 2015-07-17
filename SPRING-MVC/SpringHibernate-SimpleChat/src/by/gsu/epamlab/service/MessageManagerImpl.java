package by.gsu.epamlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.gsu.epamlab.dao.MessageDAO;
import by.gsu.epamlab.entity.MessageEntity;
import by.gsu.epamlab.entity.UserEntity;

@Service
public class MessageManagerImpl implements MessageManager {
	
	@Autowired
    private MessageDAO messageDAO;

	@Override
	@Transactional
	public void addMessage(MessageEntity newMessage, UserEntity user) {
		messageDAO.addMessage(newMessage, user);
	}

	@Override
	@Transactional
	public List<MessageEntity> getAllMessages() {
		return messageDAO.getAllMessages();
	}

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

}
