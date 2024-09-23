package kat.chatbot;

import kat.exceptions.InvalidMessageException;

/**
 * Represents a parser capable of handling various types of user messages.
 */
public interface MessageParser {

    /**
     * Handles the input message and returns an appropriate response.
     *
     * @param input The user's input message to be processed.
     * @return A string response based on the processed input.
     * @throws InvalidMessageException If the input message is invalid or cannot be processed.
     */
    String handleMessage(String input) throws InvalidMessageException;

}
