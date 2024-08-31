package command;

import java.util.ArrayList;

import exception.LevelHundredException;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ListCommand extends UserCommand {
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException {
        ArrayList<Task> tasks = taskList.getTaskList();
        ui.printTasks(tasks);
    }
}