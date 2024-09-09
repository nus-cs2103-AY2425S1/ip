package jbot.command;

import jbot.task.Task;
import jbot.util.TaskList;

/**
 * Represents a command that deletes a task from the task list.
 * This command removes a task at the specified index from the task list and prints a confirmation message
 * with the removed task. Extends {@link AddCommand} to provide common functionality for adding tasks.
 */
public class DeleteCommand extends AddCommand {

    private static final DeleteCommand instance = new DeleteCommand();

    private DeleteCommand() {
    }

    /**
     * Returns the singleton instance of {@link DeleteCommand}.
     *
     * @return The singleton instance of {@link DeleteCommand}.
     */
    public static DeleteCommand getInstance() {
        return instance;
    }

    /**
     * Executes the Delete command using the provided input string.
     * Removes the task at the index specified in the input from the task list,
     * and returns a confirmation message with the removed task, along with the total number of tasks.
     *
     * @param input The user input string containing the command and index of the task to be deleted.
     * @return A string containing the confirmation message and the removed task, followed by the total task count.
     */
    @Override
    public String run(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = TaskList.remove(taskIndex);

        // Prepare the result message
        StringBuilder result = new StringBuilder();
        result.append("Noted. I've removed this task:\n");
        result.append(String.format("  %1$s\n", task));

        // Append the result from the superclass's run method
        String superResult = super.run(input);
        result.append(superResult);

        // Return the final result
        return result.toString();
    }
}