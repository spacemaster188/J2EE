package by.gsu.epamlab.dao;

import java.util.List;

import by.gsu.epamlab.entity.MessageEntity;
import by.gsu.epamlab.entity.UserEntity;

public interface MessageDAO 
{
    public void addMessage(MessageEntity employee,UserEntity user);
    public List<MessageEntity> getAllMessages();
    
}