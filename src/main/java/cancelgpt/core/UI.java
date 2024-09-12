package cancelgpt.core;

/**
 * Represents the UI of the chatbot.
 * It manages interaction with the user.
 */
public class UI {

    /**
     * Initialises the UI object.
     */
    public UI() {
    }

    /**
     * Prints a given message to the console.
     *
     * @param message the message to print
     */
    public static void printMessageToConsole(String message) {
        System.out.println(message);
    }

    /**
     * Prints a horizontal line to the console.
     */
    public static void printHorizontalLineToConsole() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
}
