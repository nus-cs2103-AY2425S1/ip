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

    public static void main(String[] args) {
        NotgptJavaFX.main(args);
    }
}

