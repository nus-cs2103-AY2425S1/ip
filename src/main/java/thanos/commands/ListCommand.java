package thanos.commands;

import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        ArrayList<Task> result = taskList.getTaskList();
        ui.displayTasks(result, "Here are the tasks in your list:");
    }
}
