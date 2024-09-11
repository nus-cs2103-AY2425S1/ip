package axel;

import java.util.Scanner;
import java.util.List;

/**
 * Handles all interactions with the user.
 * Provides methods to display messages and interact with the user through the command line.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";
    protected Scanner scanner;

    /**
     * Initializes the {@code Ui} object with a new {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("YO! YO! It's Axel, at your service.");
        System.out.println("Hit me up with anything that I can help with!");
        System.out.println(LINE);
    }

    /**
     * Returns a welcome message as a string.
     */
    public String showWelcomeAsString() {
        return LINE + "\nYO! YO! It's Axel, at your service.\nHit me up with anything that I can help with!\n" + LINE;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("Sad to see you go... goodbye!!");
        System.out.println(LINE);
    }

    /**
     * Returns a goodbye message as a string.
     */
    public String showGoodbyeAsString() {
        return LINE + "\nSad to see you go... goodbye!!\n" + LINE;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println("HOLD YOUR HORSES!! " + message);
        System.out.println(LINE);
    }

    /**
     * Returns an error message as a string.
     *
     * @param message The error message to be returned.
     * @return The formatted error message as a string.
     */
    public String showErrorAsString(String message) {
        return LINE + "\nHOLD YOUR HORSES!! " + message + "\n" + LINE;
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks in the list after adding the new task.
     */
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been added as a string.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     * @return The formatted task added message as a string.
     */
    public String showTaskAddedAsString(Task task, int taskCount) {
        return LINE + "\nGot it. I've added this task:\n" + INDENT + task +
                "\nNow you have " + taskCount + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task      The task that was removed.
     * @param taskCount The total number of tasks in the list after removing the task.
     */
    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been removed as a string.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks in the list.
     * @return The formatted task removed message as a string.
     */
    public String showTaskRemovedAsString(Task task, int taskCount) {
        return LINE + "\nNoted. I've removed this task:\n" + INDENT + task +
                "\nNow you have " + taskCount + " tasks in the list.\n" + LINE;
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskDone(Task task) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been marked as done as a string.
     *
     * @param task The task that has been marked as done.
     * @return The formatted marked task message as a string.
     */
    public String showTaskDoneAsString(Task task) {
        return LINE + "\nNice! I've marked this task as done:\n" + task + "\n" + LINE;
    }


    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printTaskNotDone(Task task) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(LINE);
    }

    /**
     * Returns a message indicating that a task has been marked as not done as a string.
     *
     * @param task The task that has been marked as not done.
     * @return The formatted unmarked task message as a string.
     */
    public String showTaskNotDoneAsString(Task task) {
        return LINE + "\nOK, I've marked this task as not done yet:\n" + task + "\n" + LINE;
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(List<Task> tasks) {
        System.out.println(LINE);
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    /**
     * Returns the list of tasks as a string.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @return The formatted task list as a string.
     */
    public String showTaskListAsString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Prints the list of tasks that match a given search condition.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void printMatchingTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Shows the list of tasks that match a given search condition as a string.
     *
     * @param tasks The list of tasks to be printed.
     * @return The formatted list of corresponding tasks as a string
     */
    public String showMatchingTasksAsString(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return result.toString();
    }

    /**
     * Returns a help message listing all available commands and their usage.
     * The help message provides guidance to users on how to interact with Axel,
     * including how to add tasks, mark tasks as done, delete tasks, and exit the application.
     */
    public String printHelpMessage() {
        String helpMessage = LINE +"\n"
                + "Here are the available commands:\n"
                + "1. todo <description> - Adds a ToDo task.\n"
                + "2. deadline <description> /by <date> - Adds a Deadline task.\n"
                + "3. event <description> /from <start> /to <end> - Adds an Event task.\n"
                + "4. list - Lists all tasks.\n"
                + "5. mark <task_number> - Marks a task as done.\n"
                + "6. unmark <task_number> - Marks a task as not done.\n"
                + "7. delete <task_number> - Deletes a task.\n"
                + "8. find <keyword> - Finds tasks by a keyword.\n"
                + "9. bye - Exits the application.\n"
                + LINE;
        return helpMessage;
    }
}

