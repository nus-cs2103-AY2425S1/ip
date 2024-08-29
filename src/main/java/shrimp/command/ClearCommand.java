package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

public class ClearCommand implements Command {

    @Override
    public void run(TaskList tasks, Ui ui) {
        for (int i = tasks.getCount() - 1; tasks.getCount() != 0; i--) {
            tasks.deleteTask(i);
        }
    }
}
