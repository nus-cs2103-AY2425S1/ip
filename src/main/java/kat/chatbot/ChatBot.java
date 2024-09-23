package kat.chatbot;

/**
 * Represents a chatbot capable of handling user interactions.
 */
public interface ChatBot {

    /**
     * Generates a welcome message.
     *
     * @return A String containing the welcome message.
     */
    String getWelcome();

    /**
     * Generates a response based on the given user input.
     *
     * @param input The user's input message.
     * @return A String containing the chatbot's response to the user's input.
     */
    String getResponse(String input);

    /**
     * Checks if user's input is the termination command.
     *
     * @param input The user's input message.
     * @return True if input message is the termination command, false otherwise.
     */
    boolean isTerminationCommand(String input);

}
