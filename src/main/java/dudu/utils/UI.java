package dudu.utils;

import java.util.ArrayList;

import dudu.task.Task;

/**
 * Represents the user interaction portion of the chatbot
 */
public class UI {
    private static String welcomeMessage = "Hello! I'm dudu\nWhat can I do for you?";
    private static String goodbyeMessage = "Bye. Hope to see you again soon!";

    /**
     * Returns a welcome message
     *
     * @return Welcome message
     */
    public String welcomeMessage() {
        return UI.welcomeMessage;
    }

    /**
     * Returns a goodbye message
     *
     * @return Goodbye message
     */
    public String goodbyeMessage() {
        return goodbyeMessage;
    }

    /**
     * Returns a help message
     *
     * @return Help message
     */
    public String helpMessage() {
        return "Please use help to get the list of commands";
    }

    /**
     * Returns an error message
     *
     * @param e Exception which contains the message to be printed
     * @return Error message
     */
    public String showError(Exception e) {
        return e.toString();
    }

    /**
     * Returns a message to notify user of successful task addition
     *
     * @param task Task to be added
     * @param size Total number of tasks after adding the task
     * @return Message of successful task addition
     */
    public String addTask(Task task, int size) {
        String output = String.format("Got it. I've added this task:\n"
                + "    %s\nNow you have %d tasks in the list.", task, size);
        return output;
    }

    /**
     * Returns a message to notify user of task being mark as completed
     *
     * @param task Task to be marked as completed
     * @return Message of task being mark as completed
     */
    public String markTask(Task task) {
        return String.format("Nice! I've marked this task as done:\n    %s", task);
    }

    /**
     * Returns a message to notify user of task being mark as uncompleted
     *
     * @param task Task to be marked as uncompleted
     * @return Message of task being mark as uncompleted
     */
    public String unmarkTask(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n    %s", task);
    }

    /**
     * Prints a message to notify user of successful task being deleted
     *
     * @param task The task to be deleted
     */
    public String deleteTask(Task task) {
        return String.format("Noted. I've removed this task:\n    %s", task);
    }

    /**
     * Returns the current list of tasks
     *
     * @param tasklist TaskList instance containing the list of tasks to be printed
     * @return List of tasks
     */
    public String printTasks(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            return "No tasks";
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            return output.toString();
        }
    }

    /**
     * Returns a message to notify user of tasks that match the query
     *
     * @param tasks Tasks that match the query
     * @return List of tasks that matches the query
     */
    public String findTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks in your list";
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            return output.toString();
        }
    }
}
