package dudu.utils;

import java.util.ArrayList;

import dudu.task.Task;

/**
 * Represents the user interaction portion of the chatbot
 */
public class UI {
    private static String welcomeMessage = "Hello! I'm dudu\nWhat can I do for you?";
    private static String goodbyeMessage = "Bye. Hope to see you again soon!";
    // BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, HELP

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
     * Returns a welcome message
     *
     * @return Welcome message
     */
    public String getWelcomeMessage() {
        return UI.welcomeMessage;
    }

    /**
     * Returns a goodbye message
     *
     * @return Goodbye message
     */
    public String getGoodbyeMessage() {
        return UI.goodbyeMessage;
    }

    /**
     * Returns a help message
     *
     * @return Help message
     */
    public String getHelpMessage() {
        return UI.helpMessage;
    }

    /**
     * Returns an error message
     *
     * @param e Exception which contains the message to be printed
     * @return Error message
     */
    public String getErrorMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns a message to notify user of successful task addition
     *
     * @param task Task to be added
     * @param size Total number of tasks after adding the task
     * @return Message of successful task addition
     */
    public String getAddTaskMessage(Task task, int size) {
        return String.format("Got it. I've added this task:\n"
                + "    %s\nNow you have %d tasks in the list.", task, size);
    }

    /**
     * Returns a message to notify user of task being mark as completed
     *
     * @param task Task to be marked as completed
     * @return Message of task being mark as completed
     */
    public String getMarkTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n    %s", task);
    }

    /**
     * Returns a message to notify user of task being mark as uncompleted
     *
     * @param task Task to be marked as uncompleted
     * @return Message of task being mark as uncompleted
     */
    public String getUnmarkTaskMessage(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n    %s", task);
    }

    /**
     * Prints a message to notify user of successful task being deleted
     *
     * @param task The task to be deleted
     */
    public String getDeleteTaskMessage(Task task) {
        return String.format("Noted. I've removed this task:\n    %s", task);
    }

    /**
     * Returns the current list of tasks
     *
     * @param tasklist TaskList instance containing the list of tasks to be printed
     * @return List of tasks
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
     * Returns a message to notify user of tasks that match the query
     *
     * @param tasks Tasks that match the query
     * @return List of tasks that matches the query
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
