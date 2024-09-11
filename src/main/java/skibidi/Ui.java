package skibidi;

/**
 * Represents the user interface of the Skibidi program.
 */
public class Ui {
    private final String logo = "   _____ _  _______ ____ _____ _____ _____\n"
            + "  / ____| |/ /_   _|  _ \\_   _|  __ \\_   _|\n"
            + " | (___ | ' /  | | | |_) || | | |  | || |\n"
            + "  \\___ \\|  <   | | |  _ < | | | |  | || |\n"
            + "  ____) | . \\ _| |_| |_) || |_| |__| || |_\n"
            + " |_____/|_|\\_\\_____|____/_____|_____/_____|\n";

    private final String line = "____________________________________________________________\n";

    /**
     * Shows the welcome message.
     */
    public String welcomeText() {
        return " Hello! I'm SKIBIDI\n"
                + " What can I do for you?\n";
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     * @return The message to be printed.
     */
    public String outputMessage(String message) {
        return message;
    }

    /**
     * Returns the goodbye message.
     */
    public String goodbyeText() {
        return "Bye. Hope to see you again soon!";
    }
}
