import java.util.Scanner;


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
    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("bye")) {
                break;
            } else if(input.equalsIgnoreCase("list")) {
                listsTask();
            } else if (input.startsWith("mark ")) {
                handlesMark(input.substring(5).trim());
            } else if (input.startsWith("unmark ")) {
                handlesUnmark(input.substring(7).trim());
            } else if (input.startsWith("todo ")) {
                createsTodo(input.substring(5).trim());
            } else if (input.startsWith("deadline ")) {
                createsDeadline(input.substring(9).trim());
            } else if (input.startsWith("event ")) {
                createsEvent(input.substring(6).trim());
            } else {
                createsTask(input);
            }
        }
        scanner.close();
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
    public static void createsDeadline(String input) {
        String[] parts = input.split(" /by ");
        if (parts.length == 2) {
            Task task = new Deadline(parts[0], parts[1]);
            taskAdder(task);
        } else {
            System.out.println("Meow! Invalid format. Use: deadline <task description> /by <date/time>");
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
    public static void createsEvent(String input) {
        String[] parts = input.split(" /from | /to ");
        if (parts.length == 3) {
            Task task = new Event(parts[0], parts[1], parts[2]);
            taskAdder(task);
        } else {
            System.out.println("Meow! Invalid format. Use: event <task description> /from <start time> /to <end time>");
        }
    }

    /**
     * Creates a new {@code Todo} task with the specified description
     * and adds it to the task list.
     *
     * @param description The description of the todo task.
     */
    public static void createsTodo(String description){
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
    private static void handlesMark(String index) {
        try{
            int taskIndex = Integer.parseInt(index) - 1;
            try{
                tasks[taskIndex].markAsDone();
            }
            catch(NullPointerException e) {
                System.out.println("Cannot mark task as done as there is no such task added yet! Meow");
            }
        }
        catch (java.lang.NumberFormatException e) {
            System.out.println("Meow incorrect format for marking tasks! Ensure you typed : Mark (int) " +
                    "with only 1 space");
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
    private static void handlesUnmark(String index) {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            try {
                tasks[taskIndex].unmark();
            }
            catch(NullPointerException e) {
                System.out.println("Cannot unmark Task as there is no such task added yet! Meow");
            }
        }
        catch (java.lang.NumberFormatException e) {
            System.out.println("Meow incorrect format for unmarking tasks! Ensure you typed : unmark (int) " +
                    "with only 1 space");
        }
    }

    /**
     * Creates a new generic task with the provided input and adds it to the task list.
     * <p>
     * This method is used when the user input does not match specific task formats
     * (e.g., todo, deadline, event) and simply adds a basic task.
     * </p>
     *
     * @param input The description of the task to be added.
     */
    private static void createsTask(String input){
        System.out.println("____________________________________________________________");
        System.out.println("Gutti painstakingly added: " + input);
        System.out.println("____________________________________________________________");
        tasks[noOfTasks] = new Task(input);
        Gutti.noOfTasks++;
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
