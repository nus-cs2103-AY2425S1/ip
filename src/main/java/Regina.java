import java.util.Scanner;

public class Regina {
    public static void main(String[] args) {
        // Name
        final String NAME = "Regina";
        final String INDENT = "    ";
        final String LINE = INDENT + "-------------------------------";

        // Greet
        System.out.printf(LINE + "\n" + INDENT + "Hello! I'm %s \n" +
                INDENT + "What can I do for you?\n" + LINE + "\n", NAME);

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();   // Read user input
            if (userInput.equals("bye")) {
                break;
            }
            // Echo the input
            System.out.println(LINE + "\n" + INDENT + userInput + "\n" + LINE);
        }

        // Exit
        System.out.println("\n" + LINE + "\n" + INDENT +
                "Bye. Hope to see you again soon!\n" + LINE);

        scanner.close();  // Close the scanner to free up resources
    }
}
