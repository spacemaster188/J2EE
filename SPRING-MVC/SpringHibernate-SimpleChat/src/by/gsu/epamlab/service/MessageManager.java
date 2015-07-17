package by.gsu.epamlab.service;

import java.util.List;

import by.gsu.epamlab.entity.MessageEntity;
import by.gsu.epamlab.entity.UserEntity;

public interface MessageManager {
	public void addMessage(MessageEntity newMessage, UserEntity user);
    public List<MessageEntity> getAllMessages();
}
