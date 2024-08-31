package main.java.commands;

import main.java.Deadline;
import main.java.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

public class DeadlineCommand extends Command{
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.deadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
