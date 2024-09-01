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
    public void showWelcome() {
        System.out.println(logo + line
                + " Hello! I'm SKIBIDI\n"
                + " What can I do for you?\n"
                + line);
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println("Erm...");
        System.out.println("SKIBIDI: " + message);
        System.out.println("");
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}