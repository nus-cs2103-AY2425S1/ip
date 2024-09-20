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
        String output = "____________________________________________________________";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the greeting message in the console.
     *
     * @return the greeting message String.
     */
    public String greet() {
        String output = " Hello! I'm Ontos \n What can I do for you?";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the goodbye message in the console and closes the input scanner.
     *
     * @return the goodbye message String.
     */
    public String goodbye() {
        inputs.close();
        String output = " Bye. Hope to see you again soon!";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the list of tasks in the console.
     *
     * @param taskList the TaskList containing the tasks to be displayed.
     * @return the list of tasks as a String.
     */
    public String list(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        String listPrompt = " Here are the tasks in your list:\n";

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
        assert command != null : "Command cannot be null";

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
        assert taskList != null : "TaskList cannot be null";
        assert index >= 0 && index < taskList.getSize() : "Index out of bounds for TaskList";

        String completeTaskPrompt = " Nice! I've marked this task as done:\n";

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
        assert taskList != null : "TaskList cannot be null";
        assert index >= 0 && index < taskList.getSize() : "Index out of bounds for TaskList";

        String uncompleteTaskPrompt = " OK, I've marked this task as not done yet:\n";
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
        assert task != null : "Task cannot be null";

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
        assert task != null : "Task cannot be null";
        assert tasks != null : "TaskList cannot be null";

        String taskAddedPrompt = " Got it. I've added this task:\n";

        String output = taskAddedPrompt
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
        assert type != null : "Task type cannot be null";
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
        assert filteredList != null : "Filtered list cannot be null";

        String output = "Here are the matching tasks in your list:\n" + filteredList;
        System.out.println(output);
        return output;
    }

    /**
     * Displays the list of commands that can be used in the console.
     *
     * @return the list of commands as a String.
     */
    public String help() {
        String output = "Here are the commands you can use:\n"
                + "1. list: Lists all tasks in the task list.\n"
                + "2. todo <description>: Adds a todo task to the task list.\n"
                + "3. deadline <description> /by <yyyy-mm-dd>: Adds a deadline task to the task list.\n"
                + "4. event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>: Adds an event task to the task list.\n"
                + "5. mark <index>: Marks the task at the specified index as done.\n"
                + "6. unmark <index>: Marks the task at the specified index as undone.\n"
                + "7. delete <index>: Deletes the task at the specified index.\n"
                + "8. find <keyword>: Finds tasks that contain the keyword.\n"
                + "9. help: Displays the list of commands.\n"
                + "10. bye: Prints the goodbye message.";
        System.out.println(output);
        return output;
    }

}
