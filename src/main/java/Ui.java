import java.util.Scanner;

/**
 * Represents the user interface of the application.
 * Handles interactions with the user, including displaying messages and receiving user input.
 * The class makes use of a Scanner object to read commands from the user via the console.
 *
 * @author Aaron
 */
public class Ui {
    /**
     * Scanner object to read user input from the console.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    /**
     * Adds a horizontal separator line consisting of underscores to the console output.
     * This is used to visually separate different sections of the user interface.
     */
    public static void addLine() {
        System.out.println("______________________________________");
    }

    /**
     * Greets the user and begins the conversation.
     */
    public void greetUser() {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
    }

    /**
     * Ends the conversation and says bye to the user.
     */
    public static void goodbye() {
        addLine();
        System.out.println("Bye! It was so nice chatting with you.\nSee you again soon!");
        addLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
