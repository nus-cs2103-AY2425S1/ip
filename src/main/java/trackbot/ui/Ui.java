package trackbot.ui;

/**
 * Ui Handles user interface interactions and
 * has methods to display messages to the user.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello from trackbot.trackbot!\n" + "How may I assist you?";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("````````````````````````````````````````````````````````````");
    }

}
