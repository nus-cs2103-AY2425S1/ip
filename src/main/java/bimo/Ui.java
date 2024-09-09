package bimo;

import bimo.tasks.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "    " + "___________________________________";
    /**
     * Displays a horizontal line.
     */
    public String showLine() {
        return LINE;
    }
    /**
     * Displays error message for invalid tasks index.
     */
    public String showTaskNotFoundError() {
        return ("Task not found in list");
    }

    /**
     * Displays error message for invalid commands.
     */
    public String showErrorMessage() {
        return ("Something went wrong! Please try again");
    }

    /**
     * Creates response to addition of tasks.
     *
     * @param size Number of tasks inside tasklist.
     * @param task Task that is added.
     * @return Response message to addition of tasks.
     */
    public String sendAddTaskMessage(int size, Task task) {
        String word = size == 1 ? "task" : "tasks";
        return "Got it. I've added this task:\n" + "    " + task.toString()
                + "\n" + String.format("Now you have %d %s in the list.",
                size, word);
    }
}
