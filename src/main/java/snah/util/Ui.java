package snah.util;

/**
 * Class to handle the user interface of the chatbot
 */
public class Ui {
    public static final String START_DIVIDER = "____________________________________________________________";
    public static final String END_DIVIDER = "____________________________________________________________\n";
    private static final String CHAT_NAME = "Snah";

    public Ui() {
    }

    /**
     * Greets the user
     */
    public void greet() {
        start();
        print(String.format("Hello! I'm %s", CHAT_NAME));
        print("What can I do for you?");
        end();
    }

    /**
     * Prints the start of the chatbot message
     */
    public void start() {
        print(START_DIVIDER);
    }

    /**
     * Prints the end of the chatbot message
     */
    public void end() {
        print(END_DIVIDER);
    }

    /**
     * Prints formatted message
     * @param message Message to be printed
     * @param args    Arguments to be formatted
     */
    public void printf(String message, Object... args) {
        print(String.format(message, args));
    }

    /**
     * Prints a message with a newline
     * @param message Message to be printed
     */
    public void print(String message) {
        System.out.printf("\t%s\n", message);
    }

}
