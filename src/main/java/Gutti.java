import java.util.Scanner;

/**
 * Enum representing different types of commands.
 */
enum CommandType {
    BYE,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    UNKNOWN

}

/**
 * The {@code Gutti} class represents a simple chatbot that can add tasks, list them,
 * mark them as done, unmark them, and exit on command.
 * <p>
 * The chatbot greets the user, processes commands to add and list tasks,
 * and provides a farewell message before exiting when the user types the "bye" command.
 * </p>
 */

public class Gutti {

    private static Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

    /**
     * <p>
     * This method prints a welcome message and prompts the user to enter commands.
     * </p>
     */
    private static void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * <p>
     * This method prints a goodbye message and indicates that the chatbot is closing.
     * </p>
     */
    private static void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * Handles user input after the user initializes the chatbot.
     * <p>
     * This method continuously reads user input from the console.
     * It supports adding tasks (todo, deadline, event), listing tasks,
     * marking/unmarking tasks as done, and exiting the program with the "bye" command.
     * </p>
     */
    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            try {
                input = scanner.nextLine().trim(); // Trim input to handle extra spaces
                CommandType commandType = parseCommand(input);

                switch (commandType) {
                    case BYE:
                        break;
                    case LIST:
                        listsTask();
                        break;
                    case MARK:
                        if (input.length() < 5) {
                            printError("Mark");
                            break;
                        }
                        handlesMark(input.substring(5).trim());
                        break;
                    case UNMARK:
                        if (input.length() < 7) {
                            printError("Unmark");
                            break;
                        }
                        handlesUnmark(input.substring(7).trim());
                        break;
                    case TODO:
                        if (input.length() < 5) {
                            printError("Todo");
                            break;
                        }
                        createsTodo(input.substring(5).trim());
                        break;
                    case DEADLINE:
                        if (input.length() < 9) {
                            printError("Deadline");
                            break;
                        }
                        createsDeadline(input.substring(9).trim());
                        break;
                    case EVENT:
                        if (input.length() < 6) {
                            printError("Event");
                            break;
                        }
                        createsEvent(input.substring(6).trim());
                        break;
                    case UNKNOWN:
                        generateError(new GuttiException("Meow me dumb dumb me no understand me no thoughts head empty."));
                        break;
                }

                if (commandType == CommandType.BYE) {
                    break;
                }
            } catch (GuttiException e) {
                generateError(e);
            }
        }
        scanner.close();
    }

    /**
     * Parses the command from the user input and returns the corresponding {@code CommandType}.
     *
     * @param input The user input to parse.
     * @return The corresponding {@code CommandType}.
     */
    private static CommandType parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    private static void generateError(GuttiException e){
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }
    /**
     * Creates a new {@code Deadline} task with a specified description and deadline,
     * then adds it to the task list.
     * <p>
     * The input should be in the format: {@code deadline <description> /by <date/time>}.
     * </p>
     *
     * @param input The user's input containing the task description and deadline.
     */
    public static void createsDeadline(String input) throws GuttiException {
        String[] parts = input.split(" /by ");
        if (parts.length == 2) {
            Task task = new Deadline(parts[0], parts[1]);
            taskAdder(task);
        } else {
            throw new GuttiException("Invalid format. Use: deadline <task description> /by <date/time>");
        }
    }
    /**
     * Creates a new {@code Event} task with a specified description, start time,
     * and end time, then adds it to the task list.
     * <p>
     * The input should be in the format: {@code event <description> /from <start time> /to <end time>}.
     * </p>
     *
     * @param input The user's input containing the task description, start time, and end time.
     */
    public static void createsEvent(String input) throws GuttiException{
        String[] parts = input.split(" /from | /to ");
        if (parts.length == 3) {
            Task task = new Event(parts[0], parts[1], parts[2]);
            taskAdder(task);
        } else {
            throw new GuttiException("Invalid format. Use: event <task description> /from <start time> /to <end time>");
        }
    }

    /**
     * Creates a new {@code Todo} task with the specified description
     * and adds it to the task list.
     *
     * @param description The description of the todo task.
     */
    public static void createsTodo(String description) {
        Todo todoTask = new Todo(description);
        taskAdder(todoTask);
    }

    /**
     * Adds the specified task to the task list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    private static void taskAdder(Task task) {
        tasks[noOfTasks] = task;
        noOfTasks++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + noOfTasks + " tasks in the list meow!");
        System.out.println("____________________________________________________________");

    }

    /**
     * Marks the task at the specified index as done.
     * <p>
     * If the index is invalid or the task does not exist, an error message is printed.
     * </p>
     *
     * @param index The index of the task to be marked as done (1-based).
     */
    private static void handlesMark(String index) throws GuttiException {
        try{
            int taskIndex = Integer.parseInt(index) - 1;
            try{
                tasks[taskIndex].markAsDone();
            }
            catch(NullPointerException e) {
                throw new GuttiException("Cannot mark task as done as there is no such task added yet!");
            }
        }
        catch (java.lang.NumberFormatException e) {
            throw new GuttiException("Incorrect format for marking tasks! Ensure you typed : mark (int) with only 1 space");
        }
    }

    /**
     * Unmarks the task at the specified index, setting it as not done.
     * <p>
     * If the index is invalid or the task does not exist, an error message is printed.
     * </p>
     *
     * @param index The index of the task to be unmarked (1-based).
     */
    private static void handlesUnmark(String index) throws GuttiException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            try {
                tasks[taskIndex].unmark();
            }
            catch(NullPointerException e) {
                throw new GuttiException("Cannot unmark task as there is no such task added yet!");
            }
        }
        catch (java.lang.NumberFormatException e) {
            throw new GuttiException("Incorrect format for unmarking tasks! Ensure you typed : unmark (int) with only 1 space");
        }
    }

    /**
     * Lists all the tasks currently stored.
     * <p>
     * This method prints all tasks stored in the array
     * It is only called when the user types "list".
     * </p>
     */
    private static void listsTask() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }


    /**
     * Prints an error message for an empty task description based on the task type.
     *
     * @param taskType The type of task that has an empty description.
     */
    private static void printError(String taskType) {
        System.out.println("____________________________________________________________");
        System.out.println("fishfishfish!!! The description of a " + taskType + " cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * The entry point of the program.
     * @param args (not used)
     */

    public static void main(String[] args) {

        // Display the greeting message
        greetings();
        // Echos
        echo();
        // Exit message
        goodBye();
    }
}
