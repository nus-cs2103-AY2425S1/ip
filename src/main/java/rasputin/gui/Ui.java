package rasputin.gui;

import java.util.Scanner;

import rasputin.task.Task;
import rasputin.task.TaskList;

/**
 * Represents the UI that the user will interact with when using the chatbot.
 */
public class Ui {

    /**
     * Prints the list of tasks.
     *
     * @param tasks List of tasks to be printed.
     * @return String form of the list of tasks.
     */
    public static String getList(TaskList tasks) {
        assert tasks != null : "Task list cannot be null";
        return tasks.toString();
    }

    /**
     * Prints the successful addition of a task into the TaskList.
     *
     * @param task Task to be added into the list.
     * @param tasks List of tasks to be used.
     * @return String of Rasputin's response.
     */
    public static String printAddTask(Task task, TaskList tasks) {
        assert task != null : "Task cannot be null";
        assert tasks != null : "Task list cannot be null";
        String output = String.format("Added %s task:\n%s", task.getType(), task);
        output += "\nYou currently have " + tasks.size() + " task/s in your list.";
        return output;
    }

    public static String printUndoCommand() {
        return "Most recent command has been undone!";
    }

    /**
     * Prints the greeting in between two line breaks when the user starts the chatbot.
     * @return String of Rasputin's greeting.
     */
    public static String printGreeting() {
        return "Hello, I'm Rasputin!\nHow can I help you today?";
    }

    /**
     * Prints the farewell in between two line breaks when the user is done with the chatbot.
     * @return String of Rasputin's farewell.
     */
    public static String printFarewell() {
        return "Bye. See you later!";
    }

    /**
     * Prints the error message in between two line breaks.
     *
     * @param error Error message to be printed.
     * @String String of error message.
     */
    public static String printError(String error) {
        return error;
    }

}

