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
        // System.out.println("************************************************************");
        // System.out.println("Hello from trackbot.trackbot!\n" + "How may I assist you?");
        return "Hello from trackbot.trackbot!\n" + "How may I assist you?";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showBye() {
        // System.out.println("Bye. Hope to see you again soon!");
        // System.out.println("************************************************************");
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("````````````````````````````````````````````````````````````");
    }

}
