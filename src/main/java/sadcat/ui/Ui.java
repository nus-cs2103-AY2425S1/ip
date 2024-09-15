package duke.ui;

/**
 * The Ui class handles the user interface for the Duke application.
 * It provides methods for displaying startup and shutdown messages.
 */
public class Ui {

    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String greeting = "Hello! What can I do for you?";
    private static final String goodbye = "Bye! Hope to see you again soon!";

    /**
     * Constructs a new Ui object.
     */
    public Ui() {}

    /**
     * Displays the startup message, including the Duke logo.
     */
    public void startup() {
        System.out.println(greeting);
    }
    public String getGreeting() {
        return greeting;
    }

    /**
     * Displays the shutdown message.
     */
    public void shutdown() {
        System.out.println(goodbye);
    }

}
