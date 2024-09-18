package gavinchatbot.util;

import java.util.ArrayList;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import javafx.scene.layout.VBox;

/**
 * Handles the user interface of the GavinChatBot application.
 */
public class Ui {
    private VBox dialogContainer;
    private final String horizontalLine =
            "_________________________________________________________________________________\n";


    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /**
     * Returns the horizontal line string.
     * @return the horizontal line string.
     */
    public String getHorizontalLine() {
        return horizontalLine;
    }

    /**
     * Returns a welcome message to the user.
     */
    public String showWelcome() {
        return getHorizontalLine()
                + "What's up! I'm Gavin's Chat Bot!\n"
                + "What can I do for you, kind sir?\n"
                + getHorizontalLine();
    }

    /**
     * Returns a goodbye message to the user.
     */
    public String showGoodbye() {
        return getHorizontalLine()
                + "Oh, that was quick. \n"
                + "Bye. Hope to see you again soon!\n"
                + getHorizontalLine();
    }

    /**
     * Returns the list of tasks as a formatted string to the user.
     *
     * @param tasks The list of tasks.
     * @return The formatted list of tasks.
     * @throws GavinException If there is an error while retrieving the tasks.
     */
    public String showList(TaskList tasks) throws GavinException {
        StringBuilder sb = new StringBuilder();
        sb.append(getHorizontalLine());
        sb.append("Alright, here's all the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }
        sb.append(getHorizontalLine());
        return sb.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The formatted message.
     * @throws GavinException If there is an error while marking the task.
     */
    public String showMarkedTask(Task task) throws GavinException {
        return getHorizontalLine()
                + "Wow great job! This task is now marked as done:\n"
                + " " + task + "\n"
                + getHorizontalLine();
    }

    /**
     * Returns a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     * @return The formatted message.
     * @throws GavinException If there is an error while unmarking the task.
     */
    public String showUnmarkedTask(Task task) throws GavinException {
        return getHorizontalLine()
                + "Oh no... What happened... I've unmarked this task:\n"
                + " " + task + "\n"
                + getHorizontalLine();
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param size The current size of the task list.
     * @return The formatted message.
     * @throws GavinException If there is an error while adding the task.
     */
    public String showAddedTask(Task task, int size) throws GavinException {
        return getHorizontalLine()
                + "OKAY Got it. I've added this task to your list:\n"
                + " " + task + "\n"
                + "Now you have an astonishing " + size + " task(s) in the list.\n"
                + getHorizontalLine();
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param size The current size of the task list.
     * @return The formatted message.
     */
    public String showDeletedTask(Task task, int size) {
        return getHorizontalLine()
                + "Alright I gotchu. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have a total of " + size + " task(s) in the list.\n"
                + getHorizontalLine();
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to display.
     * @return The formatted error message.
     */
    public String showError(String message) {
        return getHorizontalLine()
                + "!!!ERROR!!! " + message + "\n"
                + getHorizontalLine();
    }

    /**
     * Returns an error message when there is an issue loading tasks from a file.
     *
     * @param message The specific loading error message.
     * @return The formatted loading error message.
     */
    public String showLoadingError(String message) {
        return getHorizontalLine()
                + "Error loading tasks from file: " + message + "\n"
                + getHorizontalLine();
    }
    /**
     * Returns tasks in the list that match the user task input as a formatted string.
     *
     * @param tasks The list of found tasks.
     * @return The formatted list of found tasks.
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHorizontalLine());
        sb.append("After some hard work, I have found the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        sb.append(getHorizontalLine());
        return sb.toString();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     * @return The formatted message with horizontal lines.
     */
    public String showDoneCount(String message) {
        return getHorizontalLine()
                + message + "\n"
                + getHorizontalLine();
    }

    /**
     * Returns tasks in the list that match a given tag as a formatted string.
     *
     * @param tasks The list of tasks with the given tag.
     * @param tag   The tag to filter tasks by.
     * @return The formatted list of tasks with the given tag.
     */
    public String showTaggedMessage(ArrayList<Task> tasks, String tag) {
        StringBuilder sb = new StringBuilder();
        sb.append("Okay, I have now tagged this task as '").append(tag).append("':\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return getHorizontalLine()
                + sb + "\n"
                + getHorizontalLine();
    }

    // Removed the readCommand() method as input is now handled via the GUI.
}
