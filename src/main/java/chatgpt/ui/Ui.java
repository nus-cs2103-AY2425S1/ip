package chatgpt.ui;

import chatgpt.task.Task;
import chatgpt.task.TaskList;

/**
 *  The Ui class handles receiving inputs and displaying of messages.
 */
public class Ui {
    /** Represents Name of the chatbot **/
    private static final String NAME = "ChatGPT";

    /**
     * Displays the welcome message.
     */
    public static String showWelcome() {
        String welcome = "Hello! I'm " + NAME
                + "\nWhat can I do for you?";
        return welcome;
    }

    /**
     * Displays the exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the error message from any exceptions caught.
     *
     * @param errorMessage of the exception that was caught
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Displays the message for adding of a task and
     * how many task there are left in the list.
     *
     * @param task that is being added
     * @param taskNum is the number of task in the list after addition
     */
    public String showAddTask(Task task, int taskNum) {
        String message = "Got it. I've added this task:"
        + "\n  " + task.toString()
        + "\nNow you have " + taskNum + " tasks in your list.";
        return message;
    }

    /**
     * Displays the message for deleting of a task and
     * how many task there are left in the list.
     *
     * @param task that is being deleted
     * @param taskNum is the number of task in the list after deletion
     */
    public String showDeleteTask(Task task, int taskNum) {
        String message = "Noted. I've removed this task:"
        + "\n  " + task.toString()
        + "\nNow you have " + taskNum + " tasks in your list.";

        return message;
    }

    /**
     * Displays the message for marking a task as complete.
     *
     * @param task that is completed
     */
    public String showCompleteTask(Task task) {
        String message = " Nice! I've marked this task as done: \n  "
                + task.toString();

        return message;
    }

    /**
     * Displays the message for marking a task as not complete.
     *
     * @param task that is not completed
     */
    public String showUncompleteTask(Task task) {
        String message = " OK, I've marked this task as not done yet: \n  "
                + task.toString();

        return message;
    }

    /**
     * Displays the task within the list.
     * Displays an empty list message when the given list is empty
     *
     * @param tasks is the list of Task to be displayed
     */
    public String showList(TaskList tasks) {
        String message;

        if (tasks.isEmpty()) {
            message = "Nothing has been added";
        } else {
            message = "Here are the tasks in your list:";
        }
        for (int i = 0; i < tasks.size(); i++) {
            message = message.concat("\n" + (i + 1) + ". "
                    + tasks.get(i).toString());
        }
        return message;
    }

    /**
     * Displays the tasks that contain the keyword.
     * Displays a nothing found message when the given list is empty
     *
     * @param tasks is the list of Task that contains the keyword to be displayed
     */
    public String showFound(TaskList tasks) {
        String message;

        if (tasks.isEmpty()) {
            message = "Nothing with that keyword was found";
        } else {
            message = "Here are the matching tasks in your list:";
        }
        for (int i = 0; i < tasks.size(); i++) {
            message = message.concat("\n" + (i + 1) + ". "
                    + tasks.get(i).toString());
        }
        return message;
    }

    /**
     * Displays an error message when there is a problem loading data from the save file.
     */
    public String showLoadingError() {
        String message = "There was a problem with the save file"
        + "\nYou can either: "
        + "\n(1) Fix the save file manually and restart the program"
        + "\n(2) Start from scratch";

        return message;
    }
}
