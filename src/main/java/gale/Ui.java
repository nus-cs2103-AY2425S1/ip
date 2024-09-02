package gale;
import java.util.ArrayList;

/**
 * Represents the user interface of the application. A Ui object is responsible for
 * displaying messages to the user and interacting with the user.
 *
 * @author kaikquah
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Displays a greeting message to the user when the application starts.
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gale, your friendly windy assistant.");
        System.out.println("I'll keep your deadlines, to-do's and events in my memory. What do you have to do?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Catch you on the next gust!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user when a task is added to the task list.
     * @param task the task that was added
     * @param taskCount the updated number of tasks in the task list
     */
    public void printAddedTask(Task task, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Whoosh! Task \"" + task + "\" added to my windy memory.");
        System.out.println("Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s")
            + " in the air.");
        System.out.println("Anything else?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user when a task is deleted from the task list.
     * @param task the task that was deleted
     * @param taskCount the updated number of tasks in the task list
     */
    public void showDeletedTask(Task task, int taskCount) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Poof! The wind has blown away this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s")
            + " in your windy list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the list of tasks to the user when prompted by the user.
     * @param taskList the list of tasks to be displayed
     */
    public void displayTaskList(TaskList taskList) {
        System.out.println(HORIZONTAL_LINE);
        if (taskList.isEmpty()) {
            System.out.println("No tasks breezing around now!");
        } else {
            System.out.println("You are breezy with these tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.getTask(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user when a task is marked as done or undone.
     * @param task the task that was marked
     * @param isDone the status of the task as a boolean (true if done, false if undone)
     */
    public void showMarkedTask(Task task, boolean isDone) {
        System.out.println(HORIZONTAL_LINE);
        if (isDone) {
            System.out.println("Good work! You breezed through this task!");
        } else {
            System.out.println("Tough, this task is back in the windy realm.");
        }
        System.out.println(" " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays an exception message to the user.
     * @param message the exception message to be displayed
     */
    public void showException(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message to the user when an error occurs while loading tasks from the file.
     */
    public void showLoadingError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oops! The wind blew away your tasks. Starting with a clean slate.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows the tasks that contain the keyword in their description.
     * @param foundTasks the ArrayList of tasks that contain the keyword
     * @param keyword the keyword the user is searching for
     */
    public void showFoundTasks(ArrayList<Task> foundTasks, String keyword) {
        System.out.println(HORIZONTAL_LINE);
        if (foundTasks.isEmpty()) {
            System.out.println("Oops. I combed through the clouds, but there were no tasks found with that keyword.");
        } else {
            System.out.println("Whoosh! Here are your matching tasks!");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + foundTasks.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
