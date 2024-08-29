package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int number;

    public MarkCommand(int number) {
        this.number = number;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.markTask(number);
        ui.printLine();
        ui.printWithIndent("Nice! I've marked this task as done:");
        ui.printWithIndent(task.toString());
        ui.printWithIndent(tasks.toString());
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
