package duke.ui;

/**
 * The Ui class handles the user interface for the Duke application.
 * It provides methods for displaying startup and shutdown messages.
 */
public class Ui {

    private final static String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String greeting = "Hello from\n" + logo + "\nWhat can I do for you?";
    private final static String goodbye = "Bye! Hope to see you again soon!";

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

    /**
     * Displays the shutdown message.
     */
    public void shutdown() {
        System.out.println(goodbye);
    }

}
