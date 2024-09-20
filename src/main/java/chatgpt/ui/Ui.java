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
     * Returns the welcome message.
     *
     * @return String representing the welcome message
     */
    public static String showWelcome() {
        String welcome = "Hello! I'm " + NAME
                + "\nWhat can I do for you?";

        return welcome;
    }

    /**
     * Returns the error message from any exceptions caught.
     *
     * @param errorMessage of the exception that was caught
     * @return String representing the error message
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns the message for adding of a task and
     * how many task there are left in the list.
     *
     * @param task that is being added
     * @param taskNum is the number of task in the list after addition
     * @return String showing the message for an added task
     */
    public String showAddTask(Task task, int taskNum) {
        String message = "Got it. I've added this task:"
        + "\n  " + task.toString()
        + "\nNow you have " + taskNum + " tasks in your list.";

        return message;
    }

    /**
     * Returns the message for deleting of a task and
     * how many task there are left in the list.
     *
     * @param task that is being deleted
     * @param taskNum is the number of task in the list after deletion
     * @return String showing the message for a deleted taks
     */
    public String showDeleteTask(Task task, int taskNum) {
        String message = "Noted. I've removed this task:"
        + "\n  " + task.toString()
        + "\nNow you have " + taskNum + " tasks in your list.";

        return message;
    }

    /**
     * Returns the message for marking a task as complete.
     *
     * @param task that is completed
     * @return String shows a task has been marked complete
     */
    public String showCompleteTask(Task task) {
        String message = " Nice! I've marked this task as done: \n  "
                + task.toString();

        return message;
    }

    /**
     * Returns the message for marking a task as not complete.
     *
     * @param task that is not completed
     * @return String shows a task has been marked not complete
     */
    public String showUncompleteTask(Task task) {
        String message = " OK, I've marked this task as not done yet: \n  "
                + task.toString();

        return message;
    }

    /**
     * Returns all task within the list as a String.
     * Returns an empty list message when the given list is empty.
     *
     * @param tasks is the list of Task to be displayed
     * @return String representing all the task within the list
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
     * Returns the tasks that contain the keyword.
     * Returns a nothing found message when the given list is empty
     *
     * @param tasks is the list of Task that contains the keyword to be displayed
     * @return String representing all the relevant found task
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
     * Returns the details of the given task, including the notes
     *
     * @param task to display
     * @return String representing the details of the task
     */
    public String showTask(Task task) {
        return "Here is the task you want to know more about ^-^"
                + "\n  Task details : " + task.toString()
                + "\n" + task.toShowNote();
    }

    /**
     * Returns an error message when there is a problem loading data from the save file.
     *
     * @return String representing error message when loading save data
     */
    public static String showLoadingError() {
        String message = "There was a problem with the save file"
        + "\nYou can either: "
        + "\n(1) Fix the save file manually and restart the program"
        + "\n(2) Start from scratch";

        return message;
    }
}
