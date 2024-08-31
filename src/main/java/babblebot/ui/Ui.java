package babblebot.ui;
import babblebot.task.Task;
import babblebot.task.TaskList;
import java.util.Scanner;

/**
 *
 * The Ui class handles all interactions with the user.
 * It provides methods to display messages, read user input, and show the current task list.
 */
public class Ui {
    private Scanner sc;
    private String SEPARATOR = "------------------------------------------------------------------";

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
     * Displays a welcome message to the user.
     */
    public void sayWelcome() {
        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm babblebot.BabbleBot!\nWhat can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void sayGoodbye() {
        System.out.println(SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param storedTasks The current list of tasks.
     */
    public void showTaskAdded(TaskList storedTasks) {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + storedTasks.get(storedTasks.size() - 1).toString());
        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param storedTasks The current list of tasks.
     * @param index       The index of the task to remove.
     */
    public void showRemoveMessage(TaskList storedTasks, int index) {
        System.out.println(SEPARATOR);
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + storedTasks.get(index).toString());
        storedTasks.deleteTask(index);
        System.out.println("Now you have " + storedTasks.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message when a todo task description is empty.
     */
    public void showTodoError() {
        System.out.println(SEPARATOR);
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a generic I/O error message.
     */
    public void showIOError() {
        System.out.println(SEPARATOR);
        System.out.println("OOPS!!! Something went wrong");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param storedTasks The current list of tasks.
     * @param index       The index of the task that was marked as done.
     */
    public void showMarkMessage(TaskList storedTasks, int index) {
        System.out.println(SEPARATOR);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   " + storedTasks.get(index).toString());
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param storedTasks The current list of tasks.
     * @param index       The index of the task that was marked as not done.
     */
    public void showUnmarkMessage(TaskList storedTasks, int index) {
        System.out.println(SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("   " + storedTasks.get(index).toString());
        System.out.println(SEPARATOR);
    }

    /**
     * Displays the entire list of tasks with their corresponding indices.
     *
     * @param storedTasks The current list of tasks.
     */
    public void showTaskList(TaskList storedTasks) {
        System.out.println(SEPARATOR);
        int tempCount = 1;
        for (Task t : storedTasks.getAllTasks()) {
            System.out.println(tempCount + ". " + t.toString());
            tempCount++;
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a Babblebot error message when an unrecognized command is given.
     */
    public void showBabbleBotError() {
        System.out.println(SEPARATOR);
        System.out.println("Whoopsie daisy! Looks like I got all tangled up in my words there!\n" +
                "Let's try that again in a way that might make a bit more sense.\n" +
                "What do you need help with?");
        System.out.println(SEPARATOR);
    }
}
