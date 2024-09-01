package commands;

import skibidi.Ui;
import skibidi.Command;
import storage.Task;
import storage.TaskStorage;
import skibidi.SkibidiException;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws SkibidiException {
        try {
            input = input.substring(7).trim();
            this.index = Integer.parseInt(input) - 1; // Convert 1-based to 0-based index
        } catch (NumberFormatException e) {
            throw new SkibidiException("OOPS!!! The index provided for deletion is invalid.");
        }
    }

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
