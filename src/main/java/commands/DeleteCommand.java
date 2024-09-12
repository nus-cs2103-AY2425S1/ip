package commands;

import java.io.IOException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param input The input string containing the index of the task to be deleted.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public DeleteCommand(String input) throws SkibidiException {
        this.index = parseIndex(input);
    }

    /**
     * Parses the index from the input string.
     *
     * @param input The input string containing the index.
     * @return The parsed index.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    private int parseIndex(String input) throws SkibidiException {
        try {
            String trimmedInput = input.substring(7).trim();
            return Integer.parseInt(trimmedInput) - 1; // Convert 1-based to 0-based index
        } catch (NumberFormatException e) {
            throw new SkibidiException("OOPS!!! The index provided for deletion is invalid.");
        }
    }

    /**
     * Executes the command to delete a task.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        try {
            Task task = storage.getTask(index);
            storage.deleteTask(index);
            return ui.outputMessage("Noted. I've removed this task:\n  " + task
                    + "\nNow you have " + storage.getTaskCount() + " tasks in the list.");
        } catch (SkibidiException | IOException e) {
            return ui.outputMessage(e.getMessage());
        }
    }
}
