package main.java.commands;

import main.java.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmarkTask(this.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
