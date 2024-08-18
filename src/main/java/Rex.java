import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rex {
    // Horizontal line separation
    private static String separation = "____________________________________________________________";

    // "rawr" string added to end of each print statement
    private static String rawr = "rawr";

    // "RAWRRRR" that comes with each error message
    private static String errorPrefix = "RAWRRRR!!!";

    public static void main(String[] args) {
        // Rex's greeting message
        System.out.println(separation);
        System.out.println("Hello! I'm Rex! " + rawr);
        System.out.println("What can I do for you? " + rawr);

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
            try {
                switch (command) {
                    // List all valid commands
                    case "help":
                        // help should be used alone
                        if (input.length > 1) {
                            throw new InvalidInputException(errorPrefix + " Too many arguments!");
                        }

                        listCommands();
                        break;
                    // Adding a task to list
                    case "todo":
                    case "deadline":
                    case "event":
                        // Argument cannot be empty
                        if (input.length <= 1) {
                            throw new InvalidInputException(errorPrefix + " Task description cannot be empty!");
                        }

                        String argument = input[1];
                        Task newTask = createTask(command, argument);
                        list.add(newTask);
                        break;
                    // Display items added as a numbered list
                    case "list":
                        // list should be used alone
                        if (input.length > 1) {
                            throw new InvalidInputException(errorPrefix + " Too MANY arguments!");
                        }

                        displayList(list);
                        break;
                    // Mark item in specified index as done
                    case "mark":
                        if (input.length <= 1) {
                            throw new InvalidInputException(errorPrefix + " Too FEW arguments!");
                        }

                        System.out.println(separation);
                        argument = input[1];
                        int index = Integer.parseInt(argument);
                        list.get(index - 1).markDone();
                        break;
                    // Unmark item in specified index as undone
                    case "unmark":
                        if (input.length <= 1) {
                            throw new InvalidInputException(errorPrefix + " Too FEW arguments!");
                        }

                        System.out.println(separation);
                        argument = input[1];
                        index = Integer.parseInt(argument);
                        list.get(index - 1).unmarkDone();
                        break;
                    // Rex's goodbye message, exit program
                    case "bye":
                        // bye should be used alone
                        if (input.length > 1) {
                            throw new InvalidInputException(errorPrefix + " Too many arguments!");
                        }

                        System.out.println(separation);
                        System.out.println("Bye. Hope to see you again soon! " + rawr);
                        System.out.println(separation);
                        scanner.close();
                        return;
                    // Unknown command, guide user to recognized commands
                    default:
                        System.out.println(separation);
                        System.out.println(errorPrefix + " I don't know what that means!!!");
                        System.out.println("Enter \"help\" for a list of what I do know! " + rawr);
                }
            } catch (InvalidInputException e) {
                System.out.println(separation);
                System.out.println(e.getMessage());
            } catch (InvalidTaskException e) {
                continue;
            }
        }
    }

    private static Task createTask(String command, String argument) throws InvalidTaskException {
        System.out.println(separation);
        System.out.println("Got it. I've added this task:");

        Task newTask;
        try {
            if (command.equals("todo")) {
                // Create new ToDo task
                newTask = new ToDo(argument);
            } else if (command.equals("deadline")) {
                // Create new Deadline task
                newTask = createDeadline(argument);
            } else {
                // Create new Event task
                newTask = createEvent(argument);
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            throw new InvalidTaskException();
        }

        // Print task created and number of tasks added
        System.out.println("  " + newTask);
        System.out.println("Now you have " + newTask.getNumberOfTasks() + " tasks in the list.");

        return newTask;
    }

    private static Deadline createDeadline(String argument) throws InvalidInputException {
        String[] descriptionBy = argument.split("/by", 1);
        if (descriptionBy.length < 2) {
            throw new InvalidInputException(errorPrefix + " /by not found in input!");
        }

        return new Deadline(descriptionBy[0], descriptionBy[1]);
    }

    private static Event createEvent(String argument) throws InvalidInputException {
        String[] descriptionFromTo = argument.split("/from | /to", 1);
        if (descriptionFromTo.length < 3) {
            throw new InvalidInputException(errorPrefix + " /from or /to not found in input!");
        }

        return new Event(descriptionFromTo[0], descriptionFromTo[1], descriptionFromTo[2]);
    }

    private static void displayList(List<Task> list) {
        System.out.println(separation);

        // Print statement for an empty list
        if (list.size() == 0) {
            System.out.println("The list is empty! " + rawr);
            return;
        }

        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i - 1));
        }
    }

    private static void listCommands() {
        String spaces = "  ";
        System.out.println(separation);
        System.out.println("Here are a list of valid commands and how to use them: ");
        System.out.println();

        System.out.println(spaces + "Adding tasks:");
        System.out.println(spaces + "* todo <description>");
        System.out.println(spaces + "* deadline <description> /by <date>");
        System.out.println(spaces + "* event <description> /from <date/time> /to <date/time>");
        System.out.println();

        System.out.println(spaces + "List all tasks:");
        System.out.println(spaces + "* list");
        System.out.println();

        System.out.println(spaces + "Mark/Unmark tasks:");
        System.out.println(spaces + "* mark <task number>");
        System.out.println(spaces + "* unmark <task number>");
        System.out.println();

        System.out.println(spaces + "rawr:");
        System.out.println(spaces + "* rawr");
        System.out.println();

        System.out.println(spaces + "Exit:");
        System.out.println(spaces + "* bye");
    }
}
