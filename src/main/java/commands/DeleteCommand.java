package commands;

import common.Ui;
import common.Command;
import storage.Task;
import storage.TaskStorage;
import common.SkibidiException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String input) throws SkibidiException {
        try {
            this.index = Integer.parseInt(input.trim()) - 1; // Convert 1-based to 0-based index
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
        } catch (SkibidiException e) {
            ui.printMessage(e.getMessage());
        }
        return true;
    }
}
