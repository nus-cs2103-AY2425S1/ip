package Stobberi.Command;

import Stobberi.components.TaskList;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private int taskNumber;
    public DeleteCommand(TaskList taskList, int taskNumber) {
        this.taskList = taskList;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() {
        taskList.delete(taskNumber);
    }
}