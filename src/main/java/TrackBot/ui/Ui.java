package TrackBot.ui;

/**
 * Ui Handles user interface interactions and
 * has methods to display messages to the user.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("************************************************************");
        System.out.println("Hello from TrackBot.TrackBot!\n" + "How may I assist you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("````````````````````````````````````````````````````````````");
    }

}
