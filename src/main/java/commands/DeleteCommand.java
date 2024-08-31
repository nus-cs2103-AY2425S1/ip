package main.java.commands;

import main.java.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}