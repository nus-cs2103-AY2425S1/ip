package bangmang.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import bangmang.tasks.Task;
import bangmang.tasks.TaskList;

/**
 * The Ui class handles all interactions with the user.
 * It provides methods to display messages, tasks, and errors, as well as read user input.
 * The Ui is responsible for formatting output and providing a user-friendly interface.
 */

public class Ui {
    private static final String LINE_BREAK = "---------------------------------";
    private BufferedReader br;

    /**
     * Constructor for the Ui class.
     * Initializes a BufferedReader to handle user input.
     */
    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Returns a welcome message when the chatbot starts.
     *
     * @return A string containing the welcome message.
     */
    public String showWelcome() {
        return "\nHello! I'm Ah Bang Mang.\nWhat you want sia?\n";
    }

    /**
     * Returns help information containing all available commands.
     *
     * @return A string listing all the commands available to the user.
     */
    public String showHelp() {
        return "These are all the commands you can make ah:\n" +
                "* List all tasks: list\n" +
                "* Add a todo: todo {description}\n" +
                "* Add a deadline: deadline {description} /by{date-time}\n" +
                "* Add an event: event {description} /from{date-time} /to{date-time}\n" +
                "* Mark a task as done: mark {task number}\n" +
                "* Mark a task as undone: unmark {task number}\n" +
                "* Delete a task: delete {task number}\n" +
                "* Find task: find {description}\n" +
                "* Exit chatbot: bye\n";
    }

    /**
     * Returns an exit message when the user exits the chatbot.
     *
     * @return A string containing the exit message.
     */
    public String showExit() {
        return "\nOk, I zao first then!\n";
    }

    /**
     * Returns a message indicating that there are no tasks in the task list.
     *
     * @return A string indicating an empty task list.
     */
    public String showNoTasks() {
        return "Wah shiok! No tasks at the moment!";
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The list of tasks to display.
     * @return A string containing the formatted task list.
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Siao liao! This your current task list leh...\n");
        for (int i = 0; i < tasks.size(); i++) {
            int listNumber = i + 1;
            Task t = tasks.get(i);
            sb.append(listNumber).append(". ").append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the results of a task search.
     *
     * @param tasks The list of tasks matching the search criteria.
     * @return A string containing the formatted search results.
     */
    public String showSearchResults(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("Aiyo, got no matching tasks leh...\n");
        } else {
            sb.append("Only got these few matching tasks ah...\n");
            for (int i = 0; i < tasks.size(); i++) {
                int listNumber = i + 1;
                Task t = tasks.get(i);
                sb.append(listNumber).append(". ").append(t.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns a message after successfully adding a new task to the task list.
     *
     * @param t     The task that was added.
     * @param tasks The current task list.
     * @return A string confirming the addition of the new task.
     */
    public String showAddedNewTask(Task t, TaskList tasks) {
        return "Added to list liao: " + t.toString() +
                "\nSian, now got " + tasks.size() + " tasks in your list.";
    }

    /**
     * Returns a message after successfully marking a task as done.
     *
     * @param t The task that was marked as done.
     * @return A string confirming the task was marked as done.
     */
    public String showMarkedTask(Task t) {
        return "Wah upz! You have marked this task as done: " + t.toString();
    }

    /**
     * Returns a message after successfully unmarking a task.
     *
     * @param t The task that was unmarked.
     * @return A string confirming the task was unmarked.
     */
    public String showUnmarkedTask(Task t) {
        return "Ok, I see you laze. You have marked this task as not done yet: " + t.toString();
    }

    /**
     * Returns a message after successfully deleting a task from the task list.
     *
     * @param t     The task that was deleted.
     * @param tasks The current task list.
     * @return A string confirming the deletion of the task.
     */
    public String showDeletedTask(Task t, TaskList tasks) {
        return "Wah shiok! This task no more liao: " + t.toString() +
                "\nNow got only " + tasks.size() + " tasks left.";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to display.
     * @return A string containing the error message.
     */
    public String showError(String message) {
        return "Alamak! " + message;
    }
}
