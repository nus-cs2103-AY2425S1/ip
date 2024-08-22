package chatbot;

import chatbot.exceptions.InvalidMessageException;

public interface MessageParser {

    String handleMessage(String input) throws InvalidMessageException;

}
