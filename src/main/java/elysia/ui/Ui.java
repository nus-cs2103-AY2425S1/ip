package elysia.ui;

import java.util.ArrayList;

import elysia.task.Task;

/**
 * Text UI of the application.
 */
public class Ui {
    private static String welcomeMessage = "Hi there! Did you miss me?\n"
            + "Wherever you are and whenever you need,\n"
            + "Elysia will always meet your expectations.";
    private static String exitMessage = "Alright, this time we really have to say goodbye.\n"
            + "Goodbye, Mei!";


    /**
     * Returns the welcome message for the user.
     *
     * @return The welcome message as a string.
     */
    public String getWelcomeMessage() {
        StringBuilder result = new StringBuilder();
        result.append(welcomeMessage).append("\n");
        return result.toString();
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task
     */
    public String getAddedMessage(ArrayList<Task> arrayLists, Task task) {
        StringBuilder result = new StringBuilder();

        result.append("Got it. I've added this task:\n");
        result.append("  ").append(task).append("\n");
        result.append("Now you have ").append(arrayLists.size()).append(" tasks in the list.\n");

        return result.toString();
    }

    /**
     * Returns the exit message for the user.
     *
     * @return
     */
    public String getExitMessage() {
        return exitMessage;
    }


    /**
     * Returns a string representation of the list of tasks.
     *
     * @param arrayLists
     * @return
     */
    public String printList(ArrayList<Task> arrayLists) {
        StringBuilder result = new StringBuilder();
        int n = arrayLists.size();
        result.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= n; i++) {
            Task curr = arrayLists.get(i - 1);
            result.append(i + "." + curr.toString()).append("\n");
        }
        return result.toString();
    }

    public String printEmptyList() {
        return "You've finished all the tasks today.\n"
                + "Great job!";
    }

    public String printEmptySearchList() {
        return "My dear, it looks like there's nothing that fits your description!";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task
     * @return
     */
    public String getMarkAsDoneMessage(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("Nice! I've marked this task as done:\n");
        result.append(task);
        return result.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task
     * @return
     */
    public String getUnmarkAsDoneMessage(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("OK, I've marked this task as not done yet:\n");
        result.append(task);
        return result.toString();
    }


    /**
     * Returns a message indicating that a task has been removed from the list.
     *
     * @param arrayLists
     * @param task
     * @return
     */
    public String getDeleteTaskMessage(ArrayList<Task> arrayLists, Task task) {
        StringBuilder result = new StringBuilder();
        result.append("Noted. I've removed this task:\n");
        result.append(" ").append(task).append("\n");
        result.append("Now you have " + arrayLists.size() + " tasks in the list.");
        return result.toString();
    }

    public String getClearTasksMessage() {
        return "All right, all the tasks in the list have been cleared.";
    }

}
