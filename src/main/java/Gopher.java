import java.util.Scanner;

public class Gopher {
    // Initialize the input reader
    private final static Scanner inputReader = new Scanner(System.in);

    // Common Interface elements for easy reuse
    private final static String gopherLogo = """
              ____             _              \s
             / ___| ___  _ __ | |__   ___ _ __\s
            | |  _ / _ \\| '_ \\| '_ \\ / _ \\ '__|
            | |_| | (_) | |_) | | | |  __/ |  \s
             \\____|\\___/| .__/|_| |_|\\___|_|  \s
                        |_|                   \s
            """;
    private final static String horizontalSeparator = "==================================================";

    private static void greet () {
        System.out.println(horizontalSeparator);
        System.out.println(gopherLogo);
        System.out.println("Hello! I am Gopher.\nWhat can I do for you?\n");
    }

    private static void echo (String input) {
        System.out.println(horizontalSeparator);
        System.out.println(input);
        System.out.println(horizontalSeparator + "\n");
    }

    private static void exit () {
        System.out.println(horizontalSeparator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalSeparator + "\n");
    }

    public static void main(String[] args) {
        // Greet the user when starting application
        greet();

        // Start the event loop for responding user input and query
        boolean isRunning = true;
        String userInput;
        while (isRunning) {
            userInput = inputReader.nextLine();
            if (userInput.equalsIgnoreCase("Bye")) {
                isRunning = false;
            } else {
                echo(userInput);
            }
        }

        // Send exit message to user when event loop ends
        exit();
    }
}
