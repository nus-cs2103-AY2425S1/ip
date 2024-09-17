package main.util;

import main.tasks.Task;
import main.tasks.TaskList;

/**
 * Ui deals with interactions with the user by displaying the relevant information.
 */
public class Ui {

    private StringBuilder builder;

    /**
     * A constructor for the Ui class.
     */
    public Ui() {
        builder = new StringBuilder();
    }

    public void resetBuilder() {
        builder.setLength(0);
    }

    public String getBuilder() {
        return builder.toString();
    }

    private void append(String input) {
        builder.append(input);
    }

    /**
     * Displays welcome message to user.
     */
    public void showWelcome() {
        append("Greetings Master Sir. Prince here, at your service.\n");
    }

    /**
     * Displays all tasks in a numbered list format.
     * @param taskList List of tasks.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            append("My sincere apologies Master Sir.\n");
            append("It appears to me that there are no tasks in your list.\n");
            return;
        }
        append("\"Why of course Master Sir.\n");
        append("It would be my greatest pleasure to display the tasks in your list.\n");
        int length = taskList.size();
        for (int i = 0; i < length; i++) {
            Task task = taskList.get(i);
            // formatting for numbering of list
            int listNum = i + 1;
            String numDot = listNum + ".";
            String formatted = numDot + task.toString() + "\n";
            builder.append(formatted);
        }
    }

    /**
     * Displays all tasks containing input keyword.
     * @param taskList List of tasks.
     */
    public void showMatchingTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            append("My sincere apologies Master Sir.\n");
            append("It appears to me that there are no matching tasks in your list.\n");
            return;
        }
        append("\"Why of course Master Sir.\n");
        append("It would be my greatest pleasure to display the matching tasks in your list.\n");
        int length = taskList.size();
        for (int i = 0; i < length; i++) {
            Task task = taskList.get(i);
            // formatting for numbering of list
            int listNum = i + 1;
            String numDot = listNum + ".";
            String formatted = numDot + task.toString() + "\n";
            builder.append(formatted);
        }
    }

    /**
     * Displays a list of tasks that match the keyword as specified by the user.
     */
    public void showNoMatchingTasks() {
        append("My sincere apologies Master Sir.\n");
        append("It appears to me that there are no matching tasks in your list.\n");
    }

    public void showError(String msg) {
        append(msg + "\n");
    }

    /**
     * Displays error message for the user when loading fails.
     */
    public void showLoadingError() {
        append("My sincere apologies Master Sir.\n");
        append("It appears as though there has been an error while loading tasks from the storage.\n");
    }

    public void showAdd() {
        append("The task has been appended to the list, Your Highness.\n");
    }

    public void showTaskToString(Task task) {
        append(task.toString() + "\n");
    }

    /**
     * Displays number of tasks for the user.
     * @param taskList List of tasks.
     */
    public void showNumberOfTasks(TaskList taskList) {
        append("Master Sir, there are " + taskList.size() + " tasks in your list.\n");
    }

    public void showDelete() {
        append("Noted Master Sir. I've removed this task as you have requested.\n");
    }

    public void showMark() {
        append("Upon hearing your request, I've marked this task as done Master Sir.\n");
    }

    public void showUnmark() {
        append("Upon hearing your request, I've marked this task as not done Master Sir.\n");
    }

    /**
     * Displays a farewell greeting for the user.
     */
    public void showBye() {
        append("Farewell Master Sir, I do hope to see you again soon. ");
        append("Do forgive me for any wrongdoings, Master Sir. ");
        append("For I am just a mere servant, and nothing more.\n");
    }

}
