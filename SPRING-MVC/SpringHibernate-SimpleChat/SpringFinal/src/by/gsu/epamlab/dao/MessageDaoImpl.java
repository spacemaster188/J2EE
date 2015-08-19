package by.gsu.epamlab.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.gsu.epamlab.entity.MessageEntity;
import by.gsu.epamlab.entity.UserEntity;

@Repository
public class MessageDaoImpl implements MessageDAO  {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addMessage(MessageEntity newMessage, UserEntity user) {
		newMessage.setUsersId(user.getIdUsers());
		this.sessionFactory.getCurrentSession().save(newMessage);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageEntity> getAllMessages() {
		return this.sessionFactory.getCurrentSession().createQuery("from MessageEntity").list();
	}

}
