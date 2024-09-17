package reminderebot;

import java.util.ArrayList;

import reminderebot.task.Task;
import reminderebot.task.ToDo;
import reminderebot.task.Deadline;
import reminderebot.task.Event;

/**
 * Ui represents the user interface for Reminderebot.
 */
public class Ui {
    private static final String name = "Reminderebot";
    private static final String greetingText = " Hello! I'm ***" + name + "***\n "
            + "What can I do for you?\n "
            + "For more commands you can type: help";
    private static final String goodbyeText =
            " Bye. Hope to see you again soon!";
    private static final String helpText = " Welcome to the help section! Please enter a command below:\n"
            + " help\n bye\n list\n mark <int>\n unmark <int>\n find <keyword>\n todo <taskname>\n"
            + " deadline <taskname> /by <dd/MM/yy HHmm>\n event <name> /from <dd/MM/yy HHmm> /to <dd/MM/yy HHmm>";

    /**
     * Instantiate the User Interface.
     */
    public Ui() {}

    /**
     * Returns a string representing the welcome message.
     */
    public String showWelcome() {
        return greetingText;
    }

    /**
     * Returns a string representing the goodbye message.
     */
    public String goodbye() {
        return goodbyeText;
    }

    /**
     * Returns a string representing the error message.
     * @param message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a string representing the marked task.
     * @param task
     * @return marked task
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a string representing the unmarked task.
     * @param task
     * @return unmarked task
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a string representing the deleted task.
     * @param task
     * @param index
     * @return deleted task
     */
    public String deleteTask(Task task, int index) {
        return "OK, I've removed this task:\n" + task.toString()
            + "\nNow you have " + index + " tasks in the list.";
    }

    /**
     * Returns a string representing the added task.
     * @param task
     * @param index
     * @return added task
     */
    public String addTask(Task task, int index) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + index + " tasks in the list.";
    }

    /**
     * Returns a string representing the tasks found.
     * @param tasksFound
     * @return tasks found
     */
    public String findTask(ArrayList<Task> tasksFound) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksFound.size(); i++) {
            output.append(i + 1).append(".").append(tasksFound.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns a string representing the help text
     * @return help text
     */
    public String getHelpText() {
        return helpText;
    }
}
