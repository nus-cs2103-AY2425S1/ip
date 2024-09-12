package stobberi.components;

/**
 * Represents the user interface for interacting with the chatbot.
 * Handles user greetings, farewells, and command input/output for the chatbot.
 */
public class Ui {
    private static final String NAME_OF_CHATBOT = "Stobberi";
    private static final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "What can I do for you?" +
                    "\n" +
                    "If you're unsure of what you to do, just type '?'!    ";
    private static final String GOODBYE_GREETING = "Byyyyeeeeeeeeeeeeeeee!\n" +
            "\n" +
            "Hope to talk to you again! :)";

    /**
     * Constructs a new Ui object with an initialized scanner for user input.
     */
    public Ui() {}

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
}
