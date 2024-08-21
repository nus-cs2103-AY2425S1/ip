import java.util.Scanner;

public class Megamind {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final List taskList = new List();

    public static void main(String[] args) {
        String logo = """
                  _____ \s
                 /     \\\s
                | () () |
                 \\  ^  /\s
                  ||||| \s
                  ||||| \s
                """;
        Scanner scanner = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from\n" + logo);

        greet();

        // Start a loop to read commands from the user
        while (true) {
            System.out.println(HORIZONTAL_LINE);
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            // Exit the loop if the user types "bye"
            if (command.startsWith("bye")) {
                exit();
                break;
            }

            // Print the list of tasks
            if (command.startsWith("list")) {
                System.out.println(taskList);
                continue;
            }

            // Mark a task as done
            if (command.startsWith("unmark")) {
                unmark(command);
                continue;
            }

            // Mark a task as done
            if (command.startsWith("mark")) {
                mark(command);
                continue;
            }

            // Add a to do task
            if (command.startsWith("todo")) {
                addTodo(command);
                continue;
            }

            // Add a deadline task
            if (command.startsWith("deadline")) {
                addDeadline(command);
                continue;
            }

            // Add an event task
            if (command.startsWith("event")) {
                addEvent(command);
                continue;
            }

            // If the command is not recognized, print an error message
            System.out.println("I'm sorry, but I don't know what that means. Please try again.\n");
        }
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("What's good, I'm Megamind.\nWhat can I do for you? \n");
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("See you around! \n");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a task as not done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void unmark(String command) {
        String[] words = command.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        boolean success = taskList.markTaskAsNotDone(index);

        // Depending on success of marking task as not done, print the appropriate message
        if (!success) {
            System.out.println("Invalid task number. Please try again.\n");
        } else {
            System.out.println("Alright, I've marked it as not done:\n" + taskList.get(index) + "\n");
        }
    }

    /**
     * Marks a task as done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void mark(String command) {
        String[] words = command.split(" ");
        int index = Integer.parseInt(words[1]) - 1;
        boolean success = taskList.markTaskAsDone(index);

        // Depending on success of marking task as not done, print the appropriate message
        if (!success) {
            System.out.println("Invalid task number. Please try again.\n");
        } else {
            System.out.println("Good job! You've completed a task:\n" + taskList.get(index) + "\n");
        }
    }

    /**
     * Adds a to do task to the list.
     *
     * @param command Command entered by the user.
     */
    public static void addTodo(String command) {
        String description = command.substring(5);
        taskList.add(new Todo(description));
        System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1) + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n");
    }

    /**
     * Adds a deadline task to the list.
     * If the deadline is not specified, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void addDeadline(String command) {
        if (!command.contains(" /by ")) {
            System.out.println("Please specify the deadline of the task.\n");
            return;
        }

        String[] words = command.split(" /by ");
        String description = words[0].substring(9);
        String deadline = words[1];
        taskList.add(new Deadline(description, deadline));
        System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1) + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n");
    }

    /**
     * Adds an event task to the list.
     * If the event start or end time is not specified, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void addEvent(String command) {
        if (!command.contains(" /from ") || !command.contains(" /to ")) {
            System.out.println("Please specify the start and end time of the event.\n");
            return;
        }

        String[] words = command.split(" /from ");
        String description = words[0].substring(6);
        words = words[1].split(" /to ");
        String start = words[0];
        String end = words[1];
        taskList.add(new Event(description, start, end));
        System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1) + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n");
    }
}
