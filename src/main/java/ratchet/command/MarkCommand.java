package ratchet.command;

import ratchet.exception.InvalidCommandArgumentException;
import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        try {
            Task task = tasks.markTask(index);
            ui.printLine();
            ui.printWithIndent("Nice! I've marked this task as done:");
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
