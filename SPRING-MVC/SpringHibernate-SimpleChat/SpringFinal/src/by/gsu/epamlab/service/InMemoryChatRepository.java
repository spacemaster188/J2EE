package by.gsu.epamlab.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import by.gsu.epamlab.entity.MessageEntity;

@Repository
public class InMemoryChatRepository {
	
	public static List<MessageEntity> messages = new CopyOnWriteArrayList<MessageEntity>();
	
}
