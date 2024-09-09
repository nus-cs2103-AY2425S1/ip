package bimo;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
     * Creates string representation of result list.
     *
     * @param results List of tasks containing specified words.
     * @return String representation of result list.
     */
    public String printResultsList(ArrayList<Task> results) {
        String message = "Here are the matching tasks in your list:";
        String response = IntStream.range(1, results.size())
                .mapToObj(index -> String.format("\n    %d. %s", index,
                        results.get(index - 1).toString()))
                .reduce(message, (res, next) -> res + next);
        return response;
    }
}
