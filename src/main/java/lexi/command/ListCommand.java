package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        ArrayList<Task> taskList = tasks.getTasks();
        if (taskList.isEmpty()) {
            throw new LexiException("You have no tasks in your list!");
        }
        ui.showListOfTasks(taskList);
    }
    @Override
    public String getCommandName() {
        return "LIST";
    }
}
