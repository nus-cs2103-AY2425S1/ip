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

            // User types delete; delete task
            if (splitInput[0].equals("delete")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                System.out.println("Alright! I will delete this task for you!");
                System.out.println(taskList.get(number).toString());

                taskList.remove(number);
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
                String description = splitInputByCommand[0].replaceFirst("deadline", "").trim();
                if (description.isEmpty()) {
                    System.out.println("The description of a deadline cannot be empty.");
                    continue;
                }

                try {
                    String by = splitInputByCommand[1].replaceFirst("by", "").trim();
                    taskList.add(new Deadline(description, by));
                }
                catch (Exception e) {
                    System.out.println("The stated deadline should have a date");
                    continue;
                }

                System.out.println("You now have " + taskList.size() + " tasks in your task list.");
            }
            else if (splitInput[0].equals("event")) {
                String description = splitInputByCommand[0].replaceFirst("event", "").trim();

                if (description.isEmpty()) {
                    System.out.println("The description of an event cannot be empty.");
                    continue;
                }

                try {
                    String from = splitInputByCommand[1].replaceFirst("from", "").trim();
                    String to = splitInputByCommand[2].replaceFirst("to", "").trim();
                    taskList.add(new Event(description, from, to));
                }
                catch (Exception e) {
                    System.out.println("The from and/or to cannot be empty");
                    continue;
                }

                System.out.println("You now have " + taskList.size() + " tasks in your task list.");
            }
            else if (splitInput[0].equals("todo")) {
                String description = splitInputByCommand[0].replaceFirst("todo", "").trim();
                if (description.isEmpty()) {
                    System.out.println("The description of a todo cannot be empty.");
                    continue;
                }

                taskList.add(new Todo(description));
                System.out.println("You now have " + taskList.size() + " tasks in your task list.");
            }
            else {
                System.out.println("Sorry, I do not understand what you mean. Check the README file for the list of known actions!");
            }
        }

        // Exit program message
        System.out.println("Bye. Hope to see you again soon!");
        scan.close();
    }
}
