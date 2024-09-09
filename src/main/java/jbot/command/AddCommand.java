package jbot.command;

import jbot.util.TaskList;

/**
 * Represents a command that adds a task to the task list.
 * This class provides a common implementation for commands that involve adding tasks,
 * including printing the total count of tasks in the list after the operation.
 */
public abstract class AddCommand implements JBotCommand {

    /**
     * Executes the command using the provided input string.
     * After adding the task, prints the total number of tasks in the task list.
     *
     * @param input The user input string containing the command and its arguments.
     */
    @Override
    public String run(String input) {
        int count = TaskList.size();
        return String.format("Now you have %1$s tasks in the list.\n", count);
    }
}