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

            try {
                // Exit the loop if the user types "bye"
                if (command.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                }

                // Print the list of tasks
                if (command.equalsIgnoreCase("list")) {
                    System.out.println(taskList);
                    continue;
                }

                // Print the help message
                if (command.equalsIgnoreCase("help")) {
                    help();
                    continue;
                }

                // Mark a task as not done
                if (command.startsWith("unmark ")) {
                    unmark(command);
                    continue;
                }

                // Mark a task as done
                if (command.startsWith("mark ")) {
                    mark(command);
                    continue;
                }

                // Add a to-do task
                if (command.startsWith("todo ")) {
                    addTodo(command);
                    continue;
                }

                // Add a deadline task
                if (command.startsWith("deadline ")) {
                    addDeadline(command);
                    continue;
                }

                // Add an event task
                if (command.startsWith("event ")) {
                    addEvent(command);
                    continue;
                }


                // If the command is not recognized, print an error message
                throw new InvalidCommandException("I'm sorry, but I don't know what that means. Use command 'help'" +
                        " if you require assistance.");
            } catch (InvalidCommandException | TaskNotFoundException | MissingParameterException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("What's good, I'm Megamind.\nWhat can I do for you?");
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("See you around!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the help message.
     */
    public static void help () {
        System.out.println("Here are the commands you can use:\n" +
                "1. list - List all tasks\n" +
                "2. todo <description> - Add a to-do task\n" +
                "3. deadline <description> /by <deadline> - Add a deadline task\n" +
                "4. event <description> /from <start time> /to <end time> - Add an event task\n" +
                "5. mark <task number> - Mark a task as done\n" +
                "6. unmark <task number> - Mark a task as not done\n" +
                "7. bye - Exit the program");
    }

    /**
     * Marks a task as not done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static void unmark(String command) throws InvalidCommandException, TaskNotFoundException {
        String[] words = command.split(" ");
        if (words.length < 2) {
            throw new InvalidCommandException("Task number is missing, please include it.");
        }

        int index;
        try {
            index = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format.");
        }

        boolean success = taskList.markTaskAsNotDone(index);
        if (!success) {
            throw new TaskNotFoundException("Task number does not exist.");
        }

        System.out.println("Alright, I've marked it as not done:\n" + taskList.get(index));
    }


    /**
     * Marks a task as done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static void mark(String command) throws InvalidCommandException, TaskNotFoundException {
        String[] words = command.split(" ");
        if (words.length < 2) {
            throw new InvalidCommandException("Task number is missing, please include it");
        }

        int index;
        try {
            index = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format.");
        }

        boolean success = taskList.markTaskAsDone(index);
        if (!success) {
            throw new TaskNotFoundException("Task number does not exist.");
        }

        System.out.println("Good job! You've completed a task:\n" + taskList.get(index));
    }

    /**
     * Adds a to do task to the list.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid (description is empty).
     */
    public static void addTodo(String command) throws InvalidCommandException {
        if (command.length() <= 5) {
            throw new InvalidCommandException("The description of a todo cannot be empty.");
        }

        String description = command.substring(5);
        taskList.add(new Todo(description));
        System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1) + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Adds a deadline task to the list.
     * If the deadline is not specified, print an error message.
     *
     * @param command Command entered by the user.
     * @throws MissingParameterException If the deadline is not specified.
     * @throws InvalidCommandException   If the command is invalid (description and/or deadline is empty).
     */
    public static void addDeadline(String command) throws MissingParameterException, InvalidCommandException {
        if (!command.contains(" /by ")) {
            throw new MissingParameterException("Please specify the deadline of the task.");
        }

        String[] words = command.split(" /by ");
        String description = words[0].substring(9);
        String deadline = words[1];

        if (description.isEmpty()) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }

        if (deadline.isEmpty()) {
            throw new InvalidCommandException("The deadline of a task cannot be empty.");
        }

        taskList.add(new Deadline(description, deadline));
        System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1) + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Adds an event task to the list.
     * If the event start or end time is not specified, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void addEvent(String command) throws MissingParameterException, InvalidCommandException {
        if (!command.contains(" /from ") || !command.contains(" /to ")) {
            throw new MissingParameterException("Please specify the start and end time of the event.");
        }

        String[] words = command.split(" /from ");
        String description = words[0].substring(6);
        words = words[1].split(" /to ");
        String start = words[0];
        String end = words[1];

        if (description.isEmpty()) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }

        if (start.isEmpty()) {
            throw new InvalidCommandException("The start time of an event cannot be empty.");
        }

        if (end.isEmpty()) {
            throw new InvalidCommandException("The end time of an event cannot be empty.");
        }

        taskList.add(new Event(description, start, end));
        System.out.println("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1) + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }
}
