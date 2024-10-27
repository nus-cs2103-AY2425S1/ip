package fred;

/**
 * The Ui class handles all user interactions, including printing messages
 * to the console. It is responsible for greeting the user, printing task lists,
 * and displaying messages in a consistent format.
 */
public class Ui {
    private final String line = "____________________________________________________________";

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    /**
     * Displays a message to the user, surrounded by horizontal lines for formatting.
     *
     * @param message The message to be displayed to the user.
     */
    public void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
