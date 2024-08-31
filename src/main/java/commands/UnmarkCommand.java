package main.java.commands;

import main.java.tasks.Task;
import main.java.tasks.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmarkTask(this.index);
        Task t = taskList.getTask(this.index);
        ui.showUnmarkMessage(t);
        Storage.saveToFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
