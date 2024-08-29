package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.deleteTask(number);
        ui.printLine();
        ui.printWithIndent("Noted. I've removed this task:");
        ui.printWithIndent(task.toString());
        ui.printWithIndent(tasks.toString());
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
