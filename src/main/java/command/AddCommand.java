package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.printLine();
        ui.printWithIndent("Got it. I've added " + "this task:");
        ui.printWithIndent(task.toString());
        ui.printWithIndent(tasks.toString());
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
