package devon;

/**
 * Handles user interface interactions, specifically for displaying information and exceptions to the user.
 */
public class Ui {
    /**
     * Constructs a Ui object.
     */
    public Ui() { }

    /**
     * Prints a long line of underscores to separate sections of output.
     */
    private void printLongLine() {
        String lineSeparator = "____________________";
        System.out.println("\t" + lineSeparator);
    }

    /**
     * Displays an exception message to the user.
     *
     * @param e The exception to be displayed.
     */
    protected void displayException(Exception e) {
        printLongLine();
        System.out.println("\t" + e);
        printLongLine();
    }

    /**
     * Displays a text message to the user.
     *
     * @param text The text message to be displayed.
     */
    protected void displayText(String text) {
        printLongLine();
        System.out.println(text);
        printLongLine();
    }
}
