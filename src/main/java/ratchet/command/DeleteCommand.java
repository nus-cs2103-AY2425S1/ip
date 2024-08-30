package ratchet.command;

import ratchet.exception.InvalidCommandArgumentException;
import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        try {
            Task task = tasks.deleteTask(index);
            ui.printLine();
            ui.printWithIndent("Noted. I've removed this task:");
            ui.printWithIndent(task.toString());
            ui.printWithIndent(tasks.toString());
            ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please enter a valid task index!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
