package main.java.commands;

import main.java.tasks.TaskList;
import main.java.util.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
