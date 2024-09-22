package ui;

import task.TaskList;

/**
 * Handles user interactions such as displaying messages and receiving input.
 */
public class UI {

    /**
     * Constructs a UI object for handling user input.
     */
    public UI() {
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return The welcome message string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Bob\nWhat can I do for you?\n";
    }

    /**
     * Displays a message indicating a task has been added and prints the list of tasks.
     *
     * @return The task added message along with the current list of tasks.
     */
    static String taskAddedMsg() {
        String msg = "Sure! I've added that in for you.\n";
        msg += TaskList.mainTaskList.printList();
        return msg;
    }

    /**
     * Displays a reminder message by invoking the parser's reminder handler.
     *
     * @return The reminder message string.
     */
    public String showRemindMessage() {
        return Parser.handleRemind();
    }
}
