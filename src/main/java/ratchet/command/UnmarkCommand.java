package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

public class UnmarkCommand extends Command {
    private final int number;

    public UnmarkCommand(int number) {
        this.number = number;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.unmarkTask(number);
        ui.printLine();
        ui.printWithIndent("OK, I've marked this task as not done yet:");
        ui.printWithIndent(task.toString());
        ui.printWithIndent(tasks.toString());
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
