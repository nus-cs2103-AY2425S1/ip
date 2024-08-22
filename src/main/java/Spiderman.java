import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class Spiderman {
    public static void main(String[] args) {
        // Initialise scanner
        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        ArrayList<String> tasks = new ArrayList<String>(); // Create an ArrayList object

        // Greeting users
        System.out.println("Hello! This is your friendly neighbourhood Spiderman.");
        System.out.println("What can I do for you?");

        while (true) {
            // Get users input
            String input = scan.nextLine();

            // User types bye; program terminates
            if (input.equals("bye")) {
                break;
            }

            // User types list; display list
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i+1) + ". " + tasks.get(i));
                }
                continue;
            }

            // Store users input into tasks
            tasks.add(input);

            System.out.println("added: " + input);
        }

        // Exit program message
        System.out.println("Bye. Hope to see you again soon!");
    }
}
