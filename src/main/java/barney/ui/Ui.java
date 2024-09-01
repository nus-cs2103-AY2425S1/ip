package barney.ui;

import barney.data.TaskList;
import barney.data.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    /**
     * Prints a long line separator.
     */
    public void printLongLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello, I am Barney <RAWR>, what can I do for you?");
        printLongLine();
    }

    /**
     * Prints the message after loading data.
     *
     * @param tasks The task list.
     */
    public void printLoadData(TaskList tasks) {
        System.out.println("Data loaded successfully!");
        System.out.println("You have " + tasks.size() + " tasks in the list.");
        printLongLine();
    }

    /**
     * Prints the input prompt.
     */
    public void printInput() {
        System.out.print(">>> ");
    }

    /**
     * Prints the goodbye message.
     */
    public void printBye() {
        System.out.println("Goodbye, I am Barney <RAWR>, see you next time!");
        printLongLine();
    }

    /**
     * Prints the error message when loading data.
     *
     * @param errorMessage The error message.
     */
    public void printLoadingError(String errorMessage) {
        System.out.println("Error when loading data from file: " + errorMessage);
        System.out.println("Loading with empty task list");
        printLongLine();
    }

    /**
     * Prints the error message when running a command.
     *
     * @param errorMessage The error message.
     */
    public void printChatError(String errorMessage) {
        System.out.println("Error when running: " + errorMessage);
        printLongLine();
    }

    /**
     * Prints the error message when saving data.
     *
     * @param errorMessage The error message.
     */
    public void printSaveError(String errorMessage) {
        System.out.println("Saving data to file: " + errorMessage);
        printLongLine();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The task list.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks.toStringList());
        printLongLine();
    }

    /**
     * Prints the marked task.
     *
     * @param task The marked task.
     */
    public void printMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        printLongLine();
    }

    /**
     * Prints the unmarked task.
     *
     * @param task The unmarked task.
     */
    public void printUnmarked(Task task) {
        System.out.println("OK, I've unmarked this task as not done yet:");
        System.out.println(task.toString());
        printLongLine();
    }

    /**
     * Prints the added task.
     *
     * @param task The added task.
     * @param size The size of the task list.
     */
    public void printAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", size);
        printLongLine();
    }

    /**
     * Prints the deleted task.
     *
     * @param task The deleted task.
     * @param size The size of the task list.
     */
    public void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", size);
        printLongLine();
    }

    /**
     * Prints the tasks that match the keyword
     *
     * @param tasks
     */
    public void printMatchingTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks.toStringList());
        printLongLine();
    }

    /**
     * Prints the invalid command message.
     */
    public void printInvalidCommand() {
        System.out.println("Invalid command, please try again!");
        printLongLine();
    }
}
