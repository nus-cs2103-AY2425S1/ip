package duke.ui;

/**
 * The {@code Notgpt} class is a simple command-line application that serves as an interface
 * for interacting with the user. It prints a logo and a greeting message, then passes control
 * to a parser that processes user input.
 */
public class Notgpt {
    /**
     * Prints a line divider to the console.
     * <p>
     * This method is used to create a visual line separation between different sections
     * of the console output.
     * </p>
     */
    public static void lnDiv() {
        System.out.println("___________________________________________________________________________");
    }

    /**
     * The main method serves as the entry point for the application.
     * <p>
     * It initializes the application by printing a logo and a greeting message,
     * then passes control to the {@code Parser} to process user commands. Any exceptions
     * that occur during execution are caught, logged to a file, and displayed to the user.
     * </p>
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        NotgptJavaFX.main(args);
    }
}

