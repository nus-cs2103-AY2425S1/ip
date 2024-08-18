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

        while (true) {
            System.out.println(separation);
            // Takes in user input and extract command
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(" ", 2);
            String command = input[0];

            // Process user command
            switch (command) {
            // Rex's goodbye message, exit program
            case "bye":
                System.out.println(separation);
                System.out.println("Bye. Hope to see you again soon!" + rawr);
                System.out.println(separation);
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
            // Echo user input otherwise and add to list
            default:
                 String description = echo(input);
                 newTask = new Task(description);
                 list.add(newTask);
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

    private static ToDo createToDo(String[] input) {
        System.out.println(separation);
        System.out.println("Got it. I've added this task:");

        // Loop through each word in array and enter into description (omit command)
        String description = "";
        for (int i = 1; i < input.length; i++) {
            description += input[i] + " ";
        }

        // Create new ToDo
        ToDo newToDo = new ToDo(description);
        System.out.println("  " + newToDo);
        System.out.println("Now you have " + newToDo.getNumberOfTasks() + " tasks in the list");

        return newToDo;

    }

    private static Deadline createDeadline(String[] input) {
        System.out.println(separation);
        System.out.println("Got it. I've added this task:");

        // Loop through each word in array and enter into description up to /by (omit command)
        String description = "";
        int i = 1;
        while (!input[i].equals("/by")) {
            description += input[i] + " ";
            i++;
        }

        // Skip /by, initialize by variable
        String by = "";
        i++;
        while (i < input.length) {
            by += input[i] + " ";
            i++;
        }

        // Create new Deadline
        Deadline newDeadline = new Deadline(description, by);
        System.out.println("  " + newDeadline);
        System.out.println("Now you have " + newDeadline.getNumberOfTasks() + " tasks in the list");

        return newDeadline;
    }

    private static Event createEvent(String[] input) {
        System.out.println(separation);
        System.out.println("Got it. I've added this task:");

        // Loop through each word in array and enter into description up to /from (omit command)
        String description = "";
        int i = 1;
        while (!input[i].equals("/from")) {
            description += input[i] + " ";
            i++;
        }

        // Skip /from, initialize from variable
        String from = "";
        i++;

        // Loop through and enter into from up to /to
        while (!input[i].equals("/to")) {
            from += input[i] + " ";
            i++;
        }

        // Skip /to, initialize to variable
        String to = "";
        i++;
        while (i < input.length) {
            to += input[i] + " ";
            i++;
        }

        // Create new Event
        Event newEvent = new Event(description, from, to);
        System.out.println("  " + newEvent);
        System.out.println("Now you have " + newEvent.getNumberOfTasks() + " tasks in the list");

        return newEvent;

    }

    private static void displayList(List<Task> list) {
        System.out.println(separation);
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i - 1));
        }
    }

    private static String echo(String[] input) {
        // Display input text back to user through print statement
        System.out.println(separation);
        System.out.print("added: ");

        // Loop through each word in input array
        String output = "";
        for (String word : input) {
            output += word + " ";
        }
        System.out.println(output);

        // Return item description
        return output;
    }
}
