package Stobberi.Command;

import Stobberi.components.TaskList;

public class MarkCommand extends Command {
    private TaskList taskList;
    private int taskNumber;
    public MarkCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() {
        taskList.markTask(taskNumber);
    }
}