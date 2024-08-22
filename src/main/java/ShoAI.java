import java.util.Scanner;

public class ShoAI {

    public static void main(String[] args) {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Greet the user
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm ShoAI");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Main loop: Echo commands until user types "bye"
        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user typed "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;  // Exit the loop and end the program
            }

            // Echo the user's input
            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("____________________________________________________________");
        }

        // Close the scanner
        scanner.close();
    }
}
