package by.gsu.epamlab.service;

import java.util.List;

public interface ChatRepository {

	List<String> getMessages(int messageIndex);

	void addMessage(String message);

}
