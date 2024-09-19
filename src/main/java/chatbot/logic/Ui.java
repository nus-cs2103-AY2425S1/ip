package chatbot.logic;

/**
 * Represents the UI component of the chatbot
 * Contains functionalities that interact with the user
 */
public class Ui {
    /**
     * Returns a welcome message
     * To be used whenever the chatbot is initialised
     *
     * @return A string of the welcome message
     */
    public static String sayHi() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, I'm Bobby!\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }
}
