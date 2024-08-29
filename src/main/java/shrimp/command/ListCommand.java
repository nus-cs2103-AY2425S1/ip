package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

public class ListCommand implements Command {
    @Override
    public void run(TaskList tasks, Ui ui) {
        ui.printTaskList(tasks);
    }
}
