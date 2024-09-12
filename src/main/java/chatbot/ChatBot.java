package chatbot;

/**
 * Represents a chatbot capable of handling user interactions.
 */
public interface ChatBot {

    /**
     * Starts the chatbot interaction.
     * Initiates the conversation, processes user input, and generates responses
     * until the user decides to end the conversation.
     */
    void start();

    /**
     * Generates a response based on the given user input.
     *
     * @param input The user's input message.
     * @return A String containing the chatbot's response to the user's input.
     */
    String getResponse(String input);

}
