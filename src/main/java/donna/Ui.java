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
            greeting.append("Well, look who's back!\n");
            greeting.append("I'm Donna, and I know everything :)\n");
            greeting.append("So let's get right back to it.\n");
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
        return "Bye. Try not to miss me too much.\n";
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
        message.append("Got it. Your task has been added. \n");

        if (taskCount == 1) {
            message.append("You now have me keeping track of everything, so relax. \n");
            message.append("    ").append(task).append("\n");
            message.append("This is the first task in the list. ");
        } else {
            message.append("    ").append(task);
            message.append("\nYou now have ").append(taskCount).append(" tasks in the list. \n");
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
        assert task != null : "Task should not be null";
        assert taskCount >= 0 : "Task count can not be negative";

        StringBuilder message = new StringBuilder();
        message.append("Consider it done. Task deleted: \n");
        message.append("    ").append(task);
        if (taskCount != 1) {
            message.append("\nYou've got ").append(taskCount).append(" tasks left on the list.\n");
        } else {
            message.append("\nYou now have 1 task left in the list.");
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
        assert task != null : "Task should not be null";
        StringBuilder message = new StringBuilder();
        if (isMarked) {
            message.append("Nice! I've marked this task as done. You're on fire. \n");
        } else {
            message.append("I've marked this task as not done yet. \nDon't worry, you've got this. \n");
        }
        message.append("    ").append(task).append("\n");
        return message.toString();
    }

    public String getTaskTaggedMessage(Task task, String tag) {
        assert task != null : "Task should not be null";
        StringBuilder message = new StringBuilder();
        message.append("All set. I've tagged ").append(task);
        message.append("\nYou're staying organised. That's great! \n");
        return message.toString();
    }

    /**
     * Returns the list of all tasks.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @return String representation of list of all tasks.
     */
    public String getTaskList(TaskList tasks) {
        assert tasks != null : "Task list should not be null";
        StringBuilder message = new StringBuilder();
        if (tasks.isEmpty()) {
            message.append("No tasks added yet!.\n").append(
                    "add something with todo / deadline / event!\n");
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
        assert tasks != null : "Tasks list should not be null";
        StringBuilder results = new StringBuilder();
        results.append(results);
        if (tasks.isEmpty()) {
            results.append("No tasks found matching the search :(\n");
            results.append("Maybe rephrase it? \n");
        } else {
            results.append("You’re getting more efficient by the minute.\n");
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
