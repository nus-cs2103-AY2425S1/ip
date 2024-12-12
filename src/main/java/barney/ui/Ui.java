package barney.ui;

import barney.data.TaskList;
import barney.data.task.Task;

/**
 * Represents the user interface of the application.
 */
public abstract class Ui {

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }

    /**
     * Function to return a long line separator.
     *
     * @return The long line separator.
     */
    String longLineString() {
        return "____________________________________________________________";
    }

    /**
     * Abstract method to print a long line separator.
     */
    public abstract String printLongLine();


    /**
     * Function to return the welcome string.
     *
     * @return The welcome string.
     */
    String welcomeString() {
        return "Hello, I am Barney <RAWR>, what can I do for you?";
    }

    /**
     * Abstract method to print the welcome message.
     *
     * @return The welcome message.
     */
    public abstract String printWelcome();

    /**
     * Function to return the message after loading data.
     *
     * @param tasks The task list.
     * @return The message after loading data.
     */
    String loadDataString(TaskList tasks) {
        return "Data loaded successfully!\nYou have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Abstract method to print the message after loading data.
     *
     * @param tasks The task list.
     * @return The message after loading data.
     */
    public abstract String printLoadData(TaskList tasks);


    /**
     * Returns the input prompt.
     *
     * @return The input prompt.
     */
    String inputString() {
        return ">>> ";
    }

    /**
     * Abstract method to print the input prompt.
     *
     * @return The input prompt.
     */
    public abstract String printInput();

    /**
     * String when saying bye
     *
     * @return The bye string.
     */
    String byeString() {
        return "Goodbye, I am Barney <RAWR>, see you next time!";
    }

    /**
     * Abstract method to print the bye string.
     *
     * @return The bye string.
     */
    public abstract String printBye();

    /**
     * String of loading error
     *
     * @param message The error message.
     * @return The error string.
     */
    String loadingErrorString(String message) {
        return "Error loading data: " + message;
    }

    /**
     * Abstract method to print the error String.
     *
     * @param message The error message.
     * @return The error string.
     */
    public abstract String printLoadingError(String message);

    /**
     * String of the error when running a command.
     *
     * @param errorMessage The error message.
     * @return The error string.
     */
    String chatErrorString(String errorMessage) {
        return "Error when running: " + errorMessage;
    }

    /**
     * Prints the error message when running a command.
     *
     * @param errorMessage The error message.
     * @return The error string.
     */
    public abstract String printChatError(String errorMessage);


    /**
     * The error message when saving data.
     *
     * @param errorMessage The error message.
     * @return The error string.
     */

    String saveErrorString(String errorMessage) {
        return "Saving data to file: " + errorMessage;
    }

    /**
     * Prints the error message when saving data.
     *
     * @param errorMessage The error message.
     * @return The error string.
     */

    public abstract String printSaveError(String errorMessage);


    /**
     * String of the list of tasks.
     *
     * @param tasks The list of tasks.
     * @return the list of tasks String.
     */

    String listString(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.toStringList();
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks.
     * @return the list of tasks String.
     */
    public abstract String printList(TaskList tasks);

    /**
     * String of the marked tasks.
     *
     * @param task The marked task.
     * @return The marked task string.
     */
    String markedTaskString(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Abstract method to print the marked task.
     *
     * @param task The marked task.
     * @return The marked task string.
     */
    public abstract String printMarkedTask(Task task);


    /**
     * String of the unmarked tasks.
     *
     * @param task The unmarked task.
     * @return The unmarked task string.
     */

    String unmarkedTaskString(Task task) {
        return "Nice! I've unmarked this task as undone:\n" + task.toString();
    }

    /**
     * Abstract method to print the unmarked task.
     *
     * @param task The unmarked task.
     * @return The unmarked task string.
     */
    public abstract String printUnmarkedTask(Task task);

    /**
     * String of the tagged tasks.
     *
     * @param task The tagged task.
     * @return The tagged task string.
     */
    String taggedTaskString(Task task) {
        return "Nice! I've tagged this task:\n" + task.toString();
    }

    /**
     * Abstract method to print the tagged task.
     *
     * @param task The tagged task.
     * @return The tagged task string.
     */
    public abstract String printTaggedTask(Task task);

    /**
     * String of the added tasks.
     *
     * @param task The added task.
     * @param size The size of the task list.
     * @return The added task string.
     */
    String addedTaskString(Task task, int size) {
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Abstract method to print the added task.
     *
     * @param task The added task.
     * @param size The size of the task list.
     * @return The added task string.
     */
    public abstract String printAddedTask(Task task, int size);

    /**
     * String of the deleted tasks.
     *
     * @param task The deleted task.
     * @param size The size of the task list.
     * @return The deleted task string.
     */
    String deleteTaskString(Task task, int size) {
        return "Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Abstract method to print the deleted task.
     *
     * @param task The deleted task.
     * @param size The size of the task list.
     * @return The deleted task string.
     */
    public abstract String printDeleteTask(Task task, int size);


    /**
     * String of the tasks that match the keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     * @return The tasks that match the keyword string.
     */
    String matchingTasksString(TaskList tasks) {
        return "Here are the matching tasks in your list:\n" + tasks.toStringList();
    }

    /**
     * Abstract method to print the tasks that match the keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     * @return The tasks that match the keyword string.
     */
    public abstract String printMatchingTasks(TaskList tasks);

    /**
     * String of the invalid command.
     *
     * @return The invalid command string.
     */
    String invalidCommandString() {
        return "Invalid command, please try again!";
    }

    /**
     * Abstract method to print the invalid command.
     *
     * @return The invalid command string.
     */
    public abstract String printInvalidCommand();
}
