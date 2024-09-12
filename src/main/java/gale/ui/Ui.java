package gale.ui;

import java.util.ArrayList;

import gale.task.Task;
import gale.task.TaskList;

/**
 * Represents the user interface of the application. A Ui object is responsible for
 * displaying messages to the user and interacting with the user.
 *
 * @author kaikquah
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Displays the greeting message to the user when the application starts.
     *
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello BladeRunner! I'm Gale, your friendly windy assistant.");
        System.out.println("I'll keep your deadlines, to-do's and events in my memory.");
        System.out.println("Please input your command as: task(todo/deadline/event, followed by description,"
                + " priority(optional/low/medium/high), and date/time(for deadlines and events).");
        System.out.println("Please try to keep your commands in lowercase letters!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns a String that contains the goodbye message to the user when the application exits.
     * @return the goodbye message as a String
     */
    public String exit() {
        return HORIZONTAL_LINE + "\n"
            + "Aw, it's time for you to go huh?\n"
            + "Catch you on the next gust!\n"
            + HORIZONTAL_LINE;
    }

    /**
     * Returns a String that contains a message to the user when a task is added to the task list.
     * @param task the task that was added
     * @param taskCount the updated number of tasks in the task list
     * @return the message to the user as a String
     */
    public String showAddedTask(Task task, int taskCount) {
        return HORIZONTAL_LINE + "\n"
            + "Whoosh! Task \"" + task + "\" added to my windy memory. \n"
            + "Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s")
            + " in the air.\n"
            + "Anything else?\n"
            + HORIZONTAL_LINE;
    }

    /**
     * Returns a String that contains a message to the user when a task is deleted from the task list.
     * @param task the task that was deleted
     * @param taskCount the updated number of tasks in the task list
     * @return the message to the user as a String
     */
    public String showDeletedTask(Task task, int taskCount) {
        return HORIZONTAL_LINE + "\n"
            + "Poof! The wind has blown away this task:\n"
            + " " + task + "\n"
            + "Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s")
            + " in your windy list.\n"
            + HORIZONTAL_LINE;
    }

    /**
     * Returns a String that contains the list of tasks to the user when prompted by the user.
     * @param taskList the list of tasks to be displayed
     * @return the message to the user as a String
     */
    public String displayTaskList(TaskList taskList) {
        StringBuilder sb = new StringBuilder(HORIZONTAL_LINE + "\n");
        if (taskList.isEmpty()) {
            sb.append("No tasks breezing around now!\n");
        } else {
            sb.append("You are breezy with these tasks:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(" ").append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
            }
        }
        sb.append(HORIZONTAL_LINE);
        return sb.toString();
    }

    /**
     * Returns a String that contains a message to the user when a task is marked as done or undone.
     * @param task the task that was marked
     * @param isDone the status of the task as a boolean (true if done, false if undone)
     * @return the message to the user as a String
     */
    public String showMarkedTask(Task task, boolean isDone) {
        StringBuilder sb = new StringBuilder(HORIZONTAL_LINE + "\n");
        if (isDone) {
            sb.append("Good work! You breezed through this task!\n");
        } else {
            sb.append("Tough, this task is back in the windy realm.\n");
        }
        sb.append(" ").append(task).append("\n").append(HORIZONTAL_LINE);
        return sb.toString();
    }

    /**
     * Returns a String that contains an exception message to the user.
     * @param message the exception message to be displayed
     * @return the String containing the exception message
     */
    public String showException(String message) {
        return HORIZONTAL_LINE + "\n" + message + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Returns a String that contains a message to the user when an error occurs while loading tasks from the file.
     */
    public String showLoadingError() {
        return HORIZONTAL_LINE + "\n"
            + "Oops! The wind blew away your tasks. Starting with a clean slate.\n"
            + HORIZONTAL_LINE;
    }

    /**
     * Returns a String that contains a message to the user when they search for tasks with a keyword.
     * @param foundTasks the ArrayList of tasks that contain the keyword
     * @param keyword the keyword the user is searching for
     * @return the message to the user as a String
     */
    public String showFoundTasks(ArrayList<Task> foundTasks, String keyword) {
        StringBuilder sb = new StringBuilder(HORIZONTAL_LINE + "\n");
        if (foundTasks.isEmpty()) {
            sb.append("Oops. I combed through the clouds, but there were no tasks found with that keyword.\n");
        } else {
            sb.append("Whoosh! Here are your matching tasks!\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                sb.append(" ").append(i + 1).append(". ").append(foundTasks.get(i)).append("\n");
            }
        }
        sb.append(HORIZONTAL_LINE);
        return sb.toString();
    }
}
