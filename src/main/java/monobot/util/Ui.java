package monobot.util;

import java.util.List;

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
     * @param taskList taskList contains all tasks to be printed.
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
     * @param taskList taskList contains all matching tasks to be printed.
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
     * @param task task that has just been added by user.
     * @param totalTasks Total number of tasks currently in the taskList.
     */
    public void printAddedTask(Task task, int totalTasks) {
        assert task != null : "Task added should not be null";
        assert totalTasks >= 0 : "Total tasks should not be negative";

        setLastOutput("Added: " + task + "\nNow you have " + totalTasks + " task(s) in the list");
    }

    /**
     * Prints tasks that were just deleted by user.
     *
     * @param tasks List of tasks that have just been deleted by user.
     * @param remainingTasks Number of tasks remaining in the list.
     */
    public void printDeletedTasks(List<Task> tasks, int remainingTasks) {
        StringBuilder sb = new StringBuilder("Noted! I've removed these task(s):\n");
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        sb.append("Now you have ").append(remainingTasks).append(" task(s) in the list");
        setLastOutput(sb.toString().trim());
    }


    /**
     * Prints tasks that were just marked by user.
     *
     * @param tasks List of tasks that have just been marked by user.
     */
    public void printMarkedTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder("Nice! I've marked the following task(s) as completed:\n");
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        setLastOutput(sb.toString().trim());
    }

    /**
     * Prints tasks that were just unmarked by user.
     *
     * @param tasks List of tasks that have just been unmarked by user.
     */
    public void printUnmarkedTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder("Ok! I've marked the following task(s) as incomplete:\n");
        for (Task task : tasks) {
            sb.append(task).append("\n");
        }
        setLastOutput(sb.toString().trim());
    }

    /**
     * Prints error message.
     *
     * @param message error to be displayed.
     */
    public void printError(String message) {
        assert message != null : "Error message should not be null";

        setLastOutput(message);
    }
}
