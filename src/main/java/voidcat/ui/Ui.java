package voidcat.ui;

import voidcat.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface for Void Cat.
 * It handles the display of messages to the user, such as task-related messages,
 * greetings, and farewells.
 */
public class Ui {
    private static final String FORMAT = "\t%s%n";
//    private final Scanner scanner;

    private static final String logo =
        "### ###   ## ##     ####   ### ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ### ##  ##   ##     ##     ##  ##\n" +
        "  ###    ##   ##     ##     ##  ##\n" +
        "   ##     ## ##     ####   ### ##\n";

    /**
     * Displays a welcome message with a random greeting and the application logo.
     *
     * @param greetings Array of possible greeting messages.
     * @param assistGreetings Array of possible assistance messages.
     * @return A formatted welcome message with a greeting and logo.
     */
    public static String welcome(String[] greetings, String[] assistGreetings) {
        return greetings[(int) (Math.random() * greetings.length)]
                + logo.indent(4) + assistGreetings[(int) (Math.random() * assistGreetings.length)];
    }

    /**
     * Displays a random goodbye message from the given options.
     *
     * @param exits Array of possible exit messages.
     * @return A random farewell message.
     */
    public static String goodbye(String[] exits) {
        //Display a random exit
        return exits[(int) (Math.random() * exits.length)];

    }

    /**
     * Displays a message when a task has been successfully deleted.
     *
     * @param removedTask The task that was removed.
     * @param size The current size of the task list.
     * @return A formatted message indicating the task has been removed.
     */
    public  String showDeleteTaskMessage(Task removedTask, int size) {
        return "Noted. I've removed this task:\n\t\t" + removedTask + "\nNow you have " + size + " tasks in the list";
    }

    /**
     * Displays a message when a task has been successfully marked.
     *
     * @param markedTask The task that was marked.
     * @return A formatted message indicating the task has been marked.
     */
    public  String showMarkTaskMessage(Task markedTask) {
        return "Good job! I've marked this task as done:\n\t\t" + markedTask;
    }

    /**
     * Displays a message when a task has been successfully unmarked.
     *
     * @param unmarkedTask The task that was unmarked.
     * @return A formatted message indicating the task has been unmarked.
     */
    public  String showUnmarkTaskMessage(Task unmarkedTask) {
        return "OK, I've marked this task as not done yet:\n\t\t" + unmarkedTask;
    }

    /**
     * Displays a message when a task has been successfully added.
     *
     * @param task The task that was added.
     * @param size The current size of the task list.
     * @return A formatted message indicating the task has been added.
     */
    public  String showAddTaskMessage(Task task, int size) {
        return "Got it. I've added this task:\n\t\t" + task + "\nNow you have " + size + " tasks in the list";
    }

}
