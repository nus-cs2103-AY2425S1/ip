package commands;

import skibidi.Ui;
import skibidi.Command;
import storage.Task;
import storage.TaskStorage;
import skibidi.SkibidiException;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param input The input string containing the index of the task to be deleted.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public DeleteCommand(String input) throws SkibidiException {
        try {
            input = input.substring(7).trim();
            this.index = Integer.parseInt(input) - 1; // Convert 1-based to 0-based index
        } catch (NumberFormatException e) {
            throw new SkibidiException("OOPS!!! The index provided for deletion is invalid.");
        }
    }

    /**
     * Executes the command to delete a task.
     *
     * @param ui The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return True to continue running the program.
     */
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            Task task = storage.getTask(index);
            storage.deleteTask(index);
            ui.printMessage("Noted. I've removed this task:\n  " + task +
                    "\nNow you have " + storage.getTaskCount() + " tasks in the list.");
        } catch (SkibidiException | IOException e) {
            ui.printMessage(e.getMessage());
        }
        return true;
    }
}
