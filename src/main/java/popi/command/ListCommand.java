package popi.command;

import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TaskManager taskManager) {
        ui.showTasks(tasks);
    }
}
