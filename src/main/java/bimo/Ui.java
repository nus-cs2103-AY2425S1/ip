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
        String word = wordFormat(size);
        String message = "Got it. I've added this task:\n" + "    " + task.toString()
                + "\n" + String.format("Now you have %d %s in the list.",
                size, word);
        return message;
    }

    /**
     * Creates response to addition of tasks.
     *
     * @param size  Number of tasks inside tasklist.
     * @param task Task that is added.
     * @return Response message to addition of tasks.
     */
    public String sendDeleteTaskMessage(int size, Task task) {
        String word = wordFormat(size);
        String message = "Noted. I've removed this task:\n    "
                + task.toString() + String.format("\nNow you have %d %s in the list.",
                size, word);
        return message;
    }
    /**
     * Formats word gramatically based on number of tasks in the list.
     *
     * @param size The number of tasks.
     * @return "task" if there is only one task in list else "tasks".
     */
    public String wordFormat(int size) {
        String word = size == 1 ? "task" : "tasks";
        return word;
    }

    /**
     * Creates a response to the Bye command.
     *
     * @return Farewell message.
     */
    public String sendExitMessage() {
        return "Bye!!! Thanks for chatting!";
    }

    /**
     *  Checks if task exists in the list.
     *
     * @param index Index of task to find.
     * @param tasks List of tasks.
     * @return True if task is inside list.
     */
    public boolean findTaskInList(int index, TaskList tasks) {
        if (index >= tasks.getLength() || index < 0) {
            return false;
        }
        return true;
    }
}
