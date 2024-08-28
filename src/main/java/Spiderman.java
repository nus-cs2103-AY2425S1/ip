import java.util.Arrays;
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
            String[] splitInputByCommand = input.split("/");
//            System.out.println("User input: " + Arrays.toString(splitInput));
//            System.out.println("User input split by /: " + Arrays.toString(splitInputByCommand));

            // User types bye; program terminates
            if (input.equals("bye")) {
                break;
            }

            // User types list; display list
            if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i+1) + ". " + taskList.get(i).toString());
                }
                continue;
            }

            // User types mark; mark as done
            if (splitInput[0].equals("mark")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                taskList.get(number).markAsDone();
                System.out.println("Great! I've marked this task as done:");
                System.out.println(taskList.get(number).toString());
                continue;
            }

            // User types unmark; mark as not done
            if (splitInput[0].equals("unmark")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                taskList.get(number).markAsNotDone();
                System.out.println("OK, this task will be marked as not done yet:");
                System.out.println(taskList.get(number).toString());
                continue;
            }

            // Store users input into tasks
            // Check what kind of tasks to add
            if (splitInput[0].equals("deadline")) {
                String description = splitInputByCommand[0].replaceAll("deadline ", "");
                String by = splitInputByCommand[1].replaceAll("by ", "");

                taskList.add(new Deadline(description, by));
            } else if (splitInput[0].equals("event")) {
                String description = splitInputByCommand[0].replaceAll("deadline ", "");
                String from = splitInputByCommand[1].replaceAll("from ", "");
                String to = splitInputByCommand[2].replaceAll("to ", "");

                taskList.add(new Event(description, from, to));
            } else {
                taskList.add(new Todo(splitInput[1] + " " ));
            }

            System.out.println("Cool! I'll add this to your task list!");
            System.out.println("You now have " + taskList.size() + " tasks in your task list.");
        }

        // Exit program message
        System.out.println("Bye. Hope to see you again soon!");
    }
}
