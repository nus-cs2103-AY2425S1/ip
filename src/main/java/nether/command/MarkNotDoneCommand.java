package nether.command;

import nether.task.Task;

public class MarkNotDoneCommand extends MarkCommand {
    public MarkNotDoneCommand(int taskNumber) {
        super(taskNumber);
    }

    @Override
    public void markTask(Task taskToMark) {
        taskToMark.markAsNotDone();
    }

    @Override
    public String getMarkMessage() {
        return "Understood, I've marked this task as not done:";
    }
}