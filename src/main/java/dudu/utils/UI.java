package dudu.utils;

import java.util.ArrayList;

import dudu.task.Task;

/**
 * Represents the class responsible for user interaction.
 */
public class UI {
    private static String welcomeMessage = "Hello! I'm dudu\nWhat can I do for you?";
    private static String goodbyeMessage = "Bye. Hope to see you again soon!";

    private static final String helpMessage = "These are the list of commands\n"
            + "bye - Exit the application\n"
            + "list - List the current tasks\n"
            + "todo [description] - Create a todo task\n"
            + "deadline [description] /by YYYY-MM-DD - Create a deadline task\n"
            + "event [description /from YYYY-MM-DD /to YYYY-MM-DD - Create an event task\n"
            + "mark [index] - Mark the task at [index] as completed\n"
            + "unmark [index] - Unmark the task at [index] as uncompleted\n"
            + "delete [index] - Delete the task at [index]\n"
            + "find [queries] - List the tasks with matching descriptions\n"
            + "help - List the available commands";

    /**
     * Returns a welcome message.
     */
    public String getWelcomeMessage() {
        return UI.welcomeMessage;
    }

    /**
     * Returns a goodbye message
     */
    public String getGoodbyeMessage() {
        return UI.goodbyeMessage;
    }

    /**
     * Returns a help message.
     */
    public String getHelpMessage() {
        return UI.helpMessage;
    }

    /**
     * Returns an error message.
     *
     * @param exception Exception which contains the message.
     */
    public String getErrorMessage(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Returns a message to notify user of successful task addition.
     *
     * @param task Task to be added.
     * @param size Total number of tasks after adding the task.
     */
    public String getAddTaskMessage(Task task, int size) {
        return String.format("Got it. I've added this task:\n"
                + "    %s\nNow you have %d tasks in the list.", task, size);
    }

    /**
     * Returns a message to notify user of task being mark as completed.
     *
     * @param task Task to be marked as completed.
     */
    public String getMarkTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n    %s", task);
    }

    /**
     * Returns a message to notify user of task being mark as uncompleted.
     *
     * @param task Task to be marked as uncompleted.
     */
    public String getUnmarkTaskMessage(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n    %s", task);
    }

    /**
     * Returns a message to notify user of successful task being deleted.
     *
     * @param task The task to be deleted.
     */
    public String getDeleteTaskMessage(Task task) {
        return String.format("Noted. I've removed this task:\n    %s", task);
    }

    /**
     * Returns the current list of tasks.
     *
     * @param tasklist TaskList instance containing the list of tasks.
     */
    public String getListTasksMessage(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            return "No tasks";
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            return message.toString();
        }
    }

    /**
     * Returns a message to notify user of tasks that match the query.
     *
     * @param tasks Tasks that match the query.
     */
    public String getFindTasksMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks in your list";
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            return message.toString();
        }
    }
}
