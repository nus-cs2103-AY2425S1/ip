package monobot.util;

import monobot.task.Task;

/**
 * Handles the user interface of the application.
 */
public class Ui {

    private String lastOutput = "";

    /**
     * Retrieves last output.
     */
    public String getLastOutput() {
        return lastOutput;
    }

    /**
     * Sets last output.
     */
    private void setLastOutput(String output) {
        lastOutput = output;
        System.out.println(output);
    }
    /**
     * Prints welcome greeting.
     */
    public String getGreeting() {
        return "Hello! I'm MonoBot\nWhat can I do for you?";
    }

    /**
     * Prints farewell message.
     */
    public void printFarewell() {
        setLastOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message from error when loading tasks from file.
     */
    public void showLoadingError() {
        setLastOutput("Error loading tasks from file.");
    }

    /**
     * Prints all tasks added by user.
     *
     * @param taskList taskList contains all tasks to be printed
     */
    public void printTasks(TaskList taskList) {
        assert taskList != null : "Task list should not be null";

        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("No tasks added yet");
        } else {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(String.format("%d. %s%n", i + 1, taskList.getTask(i)));
            }
        }
        setLastOutput(sb.toString());
    }

    /**
     * Prints all matching tasks is the task list.
     *
     * @param taskList taskList contains all matching tasks to be printed
     */
    public void printMatchingTasks(TaskList taskList) {
        assert taskList != null : "Task list should not be null";

        StringBuilder sb = new StringBuilder();
        if (taskList.isEmpty()) {
            sb.append("No matching tasks found");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(String.format("%d. %s%n", i + 1, taskList.getTask(i)));
            }
        }
        setLastOutput(sb.toString());
    }

    /**
     * Prints task that was just added by user.
     *
     * @param task task that has just been added by user
     * @param totalTasks Total number of tasks currently in the taskList
     */
    public void printAddedTask(Task task, int totalTasks) {
        assert task != null : "Task added should not be null";
        assert totalTasks >= 0 : "Total tasks should not be negative";

        setLastOutput("Added: " + task + "\nNow you have " + totalTasks + " task(s) in the list");
    }

    /**
     * Prints task that was just deleted by user.
     *
     * @param task task that has just been deleted by user
     * @param totalTasks Total number of tasks currently in the taskList
     */
    public void printDeletedTask(Task task, int totalTasks) {
        assert task != null : "Task deleted should not be null";
        assert totalTasks >= 0 : "Total tasks should not be negative";

        setLastOutput("Noted! I have removed this task:\n"
                + task
                + "\nNow you have " + totalTasks + " task(s) in the list");
    }

    /**
     * Prints task that was just marked by user.
     *
     * @param task task that has just been marked by user
     */
    public void printMarkedTask(Task task) {
        setLastOutput("Nice! I have marked this task as completed:\n" + task);
    }

    /**
     * Prints task that was just unmarked by user.
     *
     * @param task task that has just been unmarked by user
     */
    public void printUnmarkedTask(Task task) {
        setLastOutput("Ok! I have marked this task as incomplete:\n" + task);
    }

    /**
     * Prints error message.
     *
     * @param message error to be displayed
     */
    public void printError(String message) {
        assert message != null : "Error message should not be null";

        setLastOutput(message);
    }
}
