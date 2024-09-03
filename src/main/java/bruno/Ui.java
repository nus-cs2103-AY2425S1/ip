package bruno;

/**
 * The Ui class handles all user interaction in the Bruno application.
 * It prints messages to the console and reads user input.
 */
public class Ui {
    /**
     * Prints a horizontal line to the console for visual separation.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message based on the provided exception.
     *
     * @param e The exception whose message is to be printed.
     */
    public static void printErrorMessage(Exception e) {
        printLine();
        System.out.println(e.getMessage());
        printLine();
    }
}
