package kobe.command;

import kobe.util.Storage;
import kobe.task.TaskList;
import kobe.util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}