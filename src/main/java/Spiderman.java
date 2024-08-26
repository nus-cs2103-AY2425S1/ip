import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class Spiderman {
    public static void main(String[] args) {
        // Initialise scanner
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        // Initialise arrays for tasks
        ArrayList<Task> taskList = new ArrayList<>();

        // Greeting users
        System.out.println("Hello! This is your friendly neighbourhood Spiderman.");
        System.out.println("What can I do for you?");

        while (true) {
            // Get users input
            String input = scan.nextLine();
            String[] splitInput = input.split("\\s+");

            // User types bye; program terminates
            if (input.equals("bye")) {
                break;
            }

            // User types list; display list
            if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i+1) + ". " + "[" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
                }
                continue;
            }

            // User types mark; mark as done
            if (splitInput[0].equals("mark")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                taskList.get(number).markAsDone();
                System.out.println("Great! I've marked this task as done:");
                System.out.println("[" + taskList.get(number).getStatusIcon() + "] " + taskList.get(number).getDescription());
                continue;
            }

            // User types unmark; mark as not done
            if (splitInput[0].equals("unmark")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                taskList.get(number).markAsNotDone();
                System.out.println("OK, this task will be marked as not done yet:");
                System.out.println("[" + taskList.get(number).getStatusIcon() + "] " + taskList.get(number).getDescription());
                continue;
            }

            // Store users input into tasks
            taskList.add(new Task(input));

            System.out.println("added: " + input);
        }

        // Exit program message
        System.out.println("Bye. Hope to see you again soon!");
    }
}
