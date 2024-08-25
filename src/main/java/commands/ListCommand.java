package commands;

import java.util.ArrayList;

import exceptions.InvalidCommandException;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        ArrayList<Task> result = taskList.getTaskList();
        ui.displayTasks(result, "Here are the tasks in your list:");
    }
}
