import java.util.Scanner;  // Import the Scanner class

public class Spiderman {
    public static void main(String[] args) {
        // Initialise scanner
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        // Greeting users
        System.out.println("Hello! This is your friendly neighbourhood Spiderman.");
        System.out.println("What can I do for you?");

        while (true) {
            // Get users input
            String input = scan.nextLine();

            // User says bye; program terminates
            if (input.equals("bye")) {
                break;
            }

            System.out.println(input);
        }

        // Exit program message
        System.out.println("Bye. Hope to see you again soon!");
    }
}
