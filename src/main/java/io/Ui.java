package io;

import task.TaskList;

/**
 * The Ui class handles the user interface interactions with the user.
 * It provides the start-up and exit messages to the user.
 */
public class Ui {

    private TaskList taskList;

    /**
     * Constructs a Ui object with a given task list.
     *
     * @param taskList The task list to be managed and displayed.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the start-up message to be displayed when the application starts.
     * The message includes instructions on how to add tasks and optional tags.
     *
     * @return A string containing the start-up message with sample commands.
     */
    public static String getStartUpMessage() {
        return """
                Hello!! My name is Blitz. Try adding some tasks now!!
                Choose between adding todo, event, and deadline classes.
                Add optional tags by adding `-t` followed by your tags!!

                Here are some sample commands!!

                todo math hw -t urgent difficult
                deadline interview prep /by 1/4/2024
                event science camp /from 10/10/2024 1300 /to 11/10/2024 1800

                find -t urgent
                """;
    }

    /**
     * Returns the exit message to be displayed when the user exits the application.
     *
     * @return A string containing the exit message.
     */
    public static String getExitMessage() {
        return "Till we meet again, GOODBYE";
    }
}

