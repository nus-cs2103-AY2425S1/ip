package ontos.ui;

import java.util.Scanner;

import ontos.task.Task;
import ontos.task.TaskList;

/**
 * The Ui class handles the interaction with the user by reading input
 * and displaying relevant messages. It includes methods to greet the user,
 * display tasks, handle incorrect input, and various task-related actions.
 */
public class Ui {
    /** Greeting message displayed when the application starts. */
    private static String hello = " Hello! I'm Ontos \n What can I do for you?";

    /** Line separator used in the user interface. */
    private static String line = "____________________________________________________________";

    /** Goodbye message displayed when the application ends. */
    private static String bye = " Bye. Hope to see you again soon!";

    /** Prompt shown before listing all tasks. */
    private static String listPrompt = " Here are the tasks in your list:\n";

    /** Message shown when a task is marked as completed. */
    private static String completeTaskPrompt = " Nice! I've marked this task as done:\n";

    /** Message shown when a task is marked as not completed. */
    private static String uncompleteTaskPrompt = " OK, I've marked this task as not done yet:\n";

    /** Message shown when a task is added to the list. */
    private static String taskAdded = " Got it. I've added this task:\n";

    /** Scanner object to handle user input. */
    private Scanner inputs;

    /**
     * Constructs a new Ui object and initializes the input scanner.
     */
    public Ui() {
        this.inputs = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user, trimming leading and trailing spaces.
     *
     * @return the trimmed user input as a String.
     */
    public String readInput() {
        return inputs.nextLine().trim();
    }

    /**
     * Displays a line separator in the console.
     *
     * @return the line separator String.
     */
    public String showLine() {
        System.out.println(line);
        return line;
    }

    /**
     * Displays the greeting message in the console.
     *
     * @return the greeting message String.
     */
    public String greet() {
        System.out.println(hello);
        return hello;
    }

    /**
     * Displays the goodbye message in the console and closes the input scanner.
     *
     * @return the goodbye message String.
     */
    public String goodbye() {
        inputs.close();
        System.out.println(bye);
        return bye;
    }

    /**
     * Displays the list of tasks in the console.
     *
     * @param taskList the TaskList containing the tasks to be displayed.
     * @return the list of tasks as a String.
     */
    public String list(TaskList taskList) {
        String list = listPrompt + taskList.toString();
        System.out.println(list);
        return list;
    }

    /**
     * Displays a message indicating that a task does not exist.
     *
     * @return the message as a String.
     */
    public String taskDoesNotExist() {
        String output = " I'm sorry, but this task doesn't exist.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the correct usage format for a command in the console.
     *
     * @param command the command for which the correct usage is displayed.
     * @return the correct usage message as a String.
     */
    public String incorrectUse(String command) {
        String output = "The correct usage of '" + command + "' is: "
                + command + " n, where n is a natural number (ℕ).";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that a task has been marked as complete.
     *
     * @param taskList the TaskList containing the task.
     * @param index the index of the task in the list.
     * @return the completion message as a String.
     */
    public String mark(TaskList taskList, int index) {
        String output = completeTaskPrompt + taskList.getTaskString(index);
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that a task has been marked as incomplete.
     *
     * @param taskList the TaskList containing the task.
     * @param index the index of the task in the list.
     * @return the incomplete message as a String.
     */
    public String unmark(TaskList taskList, int index) {
        String output = uncompleteTaskPrompt + taskList.getTaskString(index);
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task the Task that was deleted.
     * @return the deletion message as a String.
     */
    public String delete(Task task) {
        String output = " Noted. I've removed this task:\n"
                + " " + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task the Task that was added.
     * @param tasks the TaskList to which the task was added.
     * @return the task added message as a String.
     */
    public String taskAdded(Task task, TaskList tasks) {
        String output = taskAdded
                + " " + task.toString() + "\n"
                + " Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that the description for a task type is empty.
     *
     * @param type the type of task.
     * @return the empty description message as a String.
     */
    public String emptyDescription(String type) {
        String output = " OOPS!!! The description of a " + type + " cannot be empty.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that a deadline task is missing a deadline.
     *
     * @return the missing deadline message as a String.
     */
    public String missingDeadline() {
        String output = " OOPS!!! A deadline task requires a deadline.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that an event task is missing start and end times.
     *
     * @return the missing start and end times message as a String.
     */
    public String missingStartAndEnd() {
        String output = " OOPS!!! An event task requires a start and end time.";
        System.out.println(output);
        return output;
    }

    /**
     * Displays a message indicating that the input was not understood.
     *
     * @return the bad input message as a String.
     */
    public String badInput() {
        String output = " OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the results of a task search in the console.
     *
     * @param filteredList the String representing the tasks that match the search.
     * @return the search results as a String.
     */
    public String findOutput(String filteredList) {
        String output = "Here are the matching tasks in your list:\n" + filteredList;
        System.out.println(output);
        return output;
    }
}
