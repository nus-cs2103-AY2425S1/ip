package stobberi.components;

/**
 * Represents the user interface for interacting with the chatbot.
 * Handles user greetings, farewells, and command input/output for the chatbot.
 */
public class Ui {
    private static final String NAME_OF_CHATBOT = "Stobberi";
    private static final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "I LOVEEEEE managing your tasks for you!\n"
                    + "\n"
                    + "What would you like to do with your task list today?\n"
                    + "\n"
                    + "If you dunno what to do, just type '?'!!  :)   ";
    private static final String GOODBYE_GREETING = "Baiii baiiii!\n"
            + "\n"
            + "Seeee yaaaa! :)";

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
