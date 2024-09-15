package babblebot.ui;

// Standard Java packages
import java.util.Scanner;

import babblebot.task.Task;
import babblebot.task.TaskList;


/**
 * The Ui class handles all interactions with the user.
 * It provides methods to display messages, read user input, and show the current task list.
 */
public class Ui {
    private static final String SEPARATOR = "------------------------------------------------------------------";
    private Scanner sc;
    /**
     * Constructs a new Ui instance, initializing the Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input from the command line.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }


    /**
     * Returns the welcome message string to the user.
     *
     * @return The welcome message as a string
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm BabbleBot!\nWhat can I do for you?";
    }

    /**
     * Returns the Goodbye message string to the user.
     *
     * @return The goodbye message as a string
     */
    public static String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message string indicating that a task has been added to the task list.
     *
     * @param storedTasks The current list of tasks.
     * @return The message string indicating task has been added
     */
    public String getTaskAddedString(TaskList storedTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("   " + storedTasks.get(storedTasks.size() - 1).toString() + "\n");
        sb.append("Now you have " + storedTasks.size() + " tasks in the list.");
        return sb.toString();
    }


    /**
     * Returns a string of the message indicating that a task has been removed from the task list.
     *
     * @param storedTasks The current list of tasks.
     * @param index       The index of the task to remove.
     * @return The string of messsage indicating task has been removed
     */
    public String getRemoveMessageString(TaskList storedTasks, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append("   " + storedTasks.get(index).toString() + "\n");
        sb.append("Now you have " + (storedTasks.size() - 1) + " tasks in the list.");
        return sb.toString();
    }

    /**
     * Returns an error message string when a todo task description is empty.
     *
     * @Return A Todo error message string
     */
    public String getTodoErrorString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Displays a generic I/O error message.
     */
    public void showIoError() {
        System.out.println(SEPARATOR);
        System.out.println("OOPS!!! Something went wrong");
        System.out.println(SEPARATOR);
    }

    /**
     * Returns a generic I/O error message string.
     *
     * @return An I/O error mesage string
     */
    public String getIoErrorString() {
        return "OOPS!!! Something went wrong";
    }


    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param storedTasks The current list of tasks.
     * @param index       The index of the task that was marked as done.
     * @return The task mark as done message as a string
     */
    public String getMarkMessageString(TaskList storedTasks, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("   " + storedTasks.get(index).toString());
        return sb.toString();
    }


    /**
     * Returns a message string indicating that a task has been marked as not done.
     *
     * @param storedTasks The current list of tasks.
     * @param index       The index of the task that was marked as not done.
     * @return The message string indicating task is mark as undone
     */
    public String getUnmarkMessageString(TaskList storedTasks, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append("   " + storedTasks.get(index).toString());
        return sb.toString();
    }


    /**
     * Retrieves the entire list of tasks with their corresponding indices.
     *
     * @param storedTasks The current list of tasks.
     */
    public String getTaskListString(TaskList storedTasks) {
        StringBuilder sb = new StringBuilder();
        int tempCount = 1;
        for (Task t : storedTasks.getAllTasks()) {
            sb.append(tempCount + ". " + t.toString() + "\n");
            tempCount++;
        }
        return sb.toString();
    }


    /**
     * Returns a Babblebot error message string when an unrecognized command is given.
     *
     * @return A Babblebot error message string
     */
    public String getBabbleBotErrorString() {
        return ("Whoopsie daisy! Looks like I got all tangled up in my words there!\n"
                + "Let's try that again in a way that might make a bit more sense.\n"
                + "What do you need help with?");
    }
}
