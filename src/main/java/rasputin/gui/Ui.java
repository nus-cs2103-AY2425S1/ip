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
        String output = String.format("Added %s task:\n%s", task.getType(), task);
        output += "\nYou currently have " + tasks.size() + " task/s in your list.";
        return output;
    }

    /**
     * Prints the greeting in between two line breaks when the user starts the chatbot.
     * @return String of Rasputin's greeting.
     */
    public static String printGreeting() {
        return "Hello, I'm Rasputin!\nWhat can I do for you?";
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

