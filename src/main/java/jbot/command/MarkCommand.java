package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

/**
 * Represents a command that marks a specific task as done.
 * This command updates the status of a task in the task list to completed.
 * Implements {@link JBotCommand} to adhere to the command interface.
 */
public class MarkCommand implements JBotCommand {
    private static final MarkCommand instance = new MarkCommand();

    private MarkCommand() {
    }

    /**
     * Returns the singleton instance of {@link MarkCommand}.
     *
     * @return The singleton instance of {@link MarkCommand}.
     */
    public static MarkCommand getInstance() {
        return instance;
    }

    /**
     * Executes the Mark command using the provided input string.
     * Marks the task at the specified index as done and returns a confirmation message.
     *
     * @param input The user input string containing the command and the task index.
     *              The index is expected to be the second part of the input string.
     * @return A string containing the confirmation message and the marked task.
     */
    @Override
    public String run(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;

        Task task = TaskList.get(taskIndex);
        task.markAsDone();

        StringBuilder result = new StringBuilder();
        result.append("Nice! I've marked this task as done:\n");
        result.append(String.format("  %1$s\n", task));

        return result.toString();
    }
}