package stobberi.components;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the chatbot.
 * Handles user greetings, farewells, and command input/output for the chatbot.
 */
public class Ui {
    private static final String NAME_OF_CHATBOT = "Stobberi";
    private static final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "What can I do for you?";
    private static final String GOODBYE_GREETING = "Byeeeeeeeeeeeeeeee! :)\n"
            + "Hope to see you soon!";
    private final Scanner scanner;

    /**
     * Constructs a new Ui object with an initialized scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a formatted phrase within a border.
     *
     * @param phrase The phrase to display.
     */
//    public static void displayForm(String phrase) {
//        System.out.println("_________________________________________________________\n"
//                + phrase
//                + "\n_________________________________________________________\n");
//    }

    /**
     * Displays a greeting message to the user.
     */
    public static String greet() {
        return HELLO_GREETING;
    }

    /**
     * Displays a farewell message to the user.
     */
    public static String sayGoodbye() {
        return GOODBYE_GREETING;
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The input line entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
