package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

/**
 * Represents a command that unmarks a specific task as not done.
 * This command updates the status of a task in the task list to not completed.
 * Implements {@link JBotCommand} to adhere to the command interface.
 */
public class UnmarkCommand implements JBotCommand {
    private static final UnmarkCommand instance = new UnmarkCommand();

    private UnmarkCommand() {
    }

    /**
     * Returns the singleton instance of {@link UnmarkCommand}.
     *
     * @return The singleton instance of {@link UnmarkCommand}.
     */
    public static UnmarkCommand getInstance() {
        return instance;
    }

    /**
     * Executes the Unmark command using the provided input string.
     * Unmarks the task at the specified index as not done and returns a confirmation message.
     *
     * @param input The user input string containing the command and the task index.
     *              The index is expected to be the second part of the input string.
     * @return A string containing the confirmation message and the unmarked task.
     */
    @Override
    public String run(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task task = TaskList.get(taskIndex);
        task.unmarkAsDone();

        StringBuilder result = new StringBuilder();
        result.append("OK, I've marked this task as not done yet:\n");
        result.append(String.format("  %1$s\n", task));

        return result.toString();
    }
}