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
        String helloString = "Hello! I'm Prince :p\n";
        String questionString = "What can I do for you?\n";
        append(String.format(helloString + questionString));
    }

    /**
     * Displays all tasks in a numbered list format.
     * @param taskList List of tasks.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            append("There are no tasks in the list!\n");
            return;
        }
        String taskGreetingString = "Here are the tasks in your list:\n";
        builder.append(taskGreetingString);
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
            append("There are no matching tasks in the list!\n");
            return;
        }
        String taskGreetingString = "Here are the matching tasks in your list:\n";
        builder.append(taskGreetingString);
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

    public void showNoMatchingTasks() {
        append("There are no tasks that match your keyword!\n");
    }

    public void showError(String msg) {
        append(msg + "\n");
    }

    public void showLoadingError() {
        append("Error loading tasks from storage!\n");
    }

    public void showAdd() {
        append("Got it. I've added this task:\n");
    }

    public void showTaskToString(Task task) {
        append(task.toString() + "\n");
    }

    /**
     * Displays number of tasks for the user.
     * @param taskList List of tasks.
     */
    public void showNumberOfTasks(TaskList taskList) {
        append(taskList.size() + " tasks in the list.\n");
    }

    public void showDelete() {
        append("Noted. I've removed this task:\n");
    }

    public void showMark() {
        append("Nice! I've marked this task as done:\n");
    }

    public void showUnmark() {
        append("OK, I've marked this task as not done yet:\n");
    }

    public void showBye() {
        append("Bye. Hope to see you again soon!\n");
    }

}
