package donna;

import java.util.List;

import donna.task.Task;
import donna.task.TaskList;

/**
 * Contains user interface methods for interacting with the application.
 * Handles displaying messages, task lists, and error information to the user.
 */
public class Ui {
    private boolean wasDataLoaded;

    public Ui(boolean wasDataLoaded) {
        this.wasDataLoaded = wasDataLoaded;
    }

    private static String dashedLine() {
        return "____________________________________________________________________\n";
    }

    private static String donnaLogo() {
        return " __      \n"
                + "|  _  \\  __  _ __   _ _   __  \n"
                + "|  |  |  | / _ \\| '_  \\ | '_ \\ / _` |\n"
                + "|  |_| |  (_) |  | |  |  |  |   | (_|  |\n"
                + "|___/ \\__/|_| |_|_|  |_|\\_,_|\n";
    }


    /**
     * Returns a greeting message to the user.
     *
     * @return The greeting message.
     */
    public String getGreeting() {
        StringBuilder greeting = new StringBuilder();
        if (wasDataLoaded) {
            greeting.append("Hello! I'm Donna, I know everything :)\n");
            greeting.append("We have had a chat before! Let's resume :)\n");
        } else {
            greeting.append("Hello! I'm Donna\nWhat can I do for you?");
        }
        return greeting.toString();
    }

    /**
     * Returns a goodbye message to the user.
     *
     * @return Exit message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     * @return The task added message.
     */
    public String getTaskAddedMessage(Task task, int taskCount) {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task: \n");
        message.append("    ").append(task).append("\n");

        if (taskCount == 1) {
            message.append("This is the first task in the list. ");
        } else {
            message.append("You now have ").append(taskCount).append(" tasks in the list. \n");
        }
        return message.toString();
    }

    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     * @return The task deleted message.
     */
    public String getTaskDeletedMessage(Task task, int taskCount) {
        StringBuilder message = new StringBuilder();
        message.append("Alright. The following task has been deleted: \n");
        message.append("    ").append(task);
        if (taskCount != 1) {
            message.append("You now have ").append(taskCount).append(" tasks in the list.\n");
        } else {
            message.append("You now have 1 task left in the list.");
        }
        return message.toString();
    }

    /**
     * Returns a message confirming the status of a task.
     *
     * @param task The task that was marked.
     * @param isMarked True if the task is marked as done; false otherwise.
     * @return Message confirming status of a task.
     */
    public String getTaskMarkedMessage(Task task, boolean isMarked) {
        StringBuilder message = new StringBuilder();
        if (isMarked) {
            message.append("Nice! I've marked this task as done: \n");
        } else {
            message.append("OK, I have marked this task as not done yet: \n");
        }
        message.append("    ").append(task).append("\n");
        return message.toString();
    }

    /**
     * Returns the list of all tasks.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @return String representation of list of all tasks.
     */
    public String getTaskList(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("No tasks added to the list yet.\n").append(
                    "use todo / deadline / event to add tasks to the list!\n");
        } else {
            message.append("There are ").append(tasks.size()).append(" task(s) in the list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return message.toString();
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to be printed.
     * @return The generated error message.
     */
    public String getErrorMessage(String message) {
        return message + "\n";
    }

    /**
     * Returns the results of a search query.
     *
     * @param tasks The list of tasks that match the search.
     * @return The results of the user's search.
     */
    public String findResults(List<Task> tasks) {
        StringBuilder results = new StringBuilder();
        results.append(results);
        if (tasks.isEmpty()) {
            results.append("No tasks found matching the search criteria :(\n");
        } else {
            results.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                results.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
            }
        }
        return results.toString();
    }

    /**
     * Displays Donna's response (for Text UI).
     */
    public void display(String response) {
        System.out.println(dashedLine());
        System.out.println(response);
        System.out.println(dashedLine());
    }
}
