import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rex {
    // Horizontal line separation
    private static String separation = "____________________________________________________________";

    // "rawr" string added to end of each print statement for customization
    private static String rawr = " rawr";
    public static void main(String[] args) {
        // Rex's greeting message
        System.out.println(separation);
        System.out.println("Hello! I'm Rex!" + rawr);
        System.out.println("What can I do for you?" + rawr);

        // ArrayList to store added tasks
        ArrayList<Task> list = new ArrayList<>();

        // Initialize scanner to take in user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(separation);
            // Takes in user input and extract command
            String[] input = scanner.nextLine().split(" ", 2);
            String command = input[0];

            // Process user command
            switch (command) {
            // Rex's goodbye message, exit program
            case "bye":
                System.out.println(separation);
                System.out.println("Bye. Hope to see you again soon!" + rawr);
                System.out.println(separation);
                scanner.close();
                return;
            // Adding a task to list
            case "todo":
            case "deadline":
            case "event":
                String argument = input[1];
                Task newTask = createTask(command, argument);
                list.add(newTask);
                break;
            // Display items added as a numbered list
            case "list":
                displayList(list);
                break;
            // Mark item in specified index as done
            case "mark":
                System.out.println(separation);
                argument = input[1];
                int index = Integer.parseInt(argument);
                list.get(index - 1).markDone();
                break;
            // Unmark item in specified index as undone
            case "unmark":
                System.out.println(separation);
                argument = input[1];
                index = Integer.parseInt(argument);
                list.get(index - 1).unmarkDone();
                break;
            // Echo user input otherwise
            default:
                 echo(input);
                 break;
            }
        }
    }

    private static Task createTask(String command, String argument) {
        System.out.println(separation);
        System.out.println("Got it. I've added this task:");

        Task newTask;
        if (command.equals("todo")) {
            // Create new ToDo task
            newTask = new ToDo(argument);
        } else if (command.equals("deadline")) {
            // Create new Deadline task, include deadline date
            String[] descriptionBy = argument.split("/by");
            newTask = new Deadline(descriptionBy[0], descriptionBy[1]);
        } else if (command.equals("event")) {
            // Create new Event task, include from and to periods
            String[] descriptionFromTo = argument.split("/from | /to");
            newTask = new Event(descriptionFromTo[0], descriptionFromTo[1], descriptionFromTo[2]);
        } else {
            // No command matches
            return null;
        }

        // Print task created and number of tasks added
        System.out.println("  " + newTask);
        System.out.println("Now you have " + newTask.getNumberOfTasks() + " tasks in the list.");

        return newTask;
    }

    private static void displayList(List<Task> list) {
        System.out.println(separation);
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i - 1));
        }
    }

    private static void echo(String[] input) {
        // Display input text back to user through print statement
        System.out.println(separation);

        // Loop through each word in input array
        String output = "";
        for (String word : input) {
            output += word + " ";
        }
        System.out.println(output + rawr);
    }
}
