package nether.command;

import nether.task.Task;

public class MarkDoneCommand extends MarkCommand {
    public MarkDoneCommand(int taskNumber) {
        super(taskNumber);
    }

    @Override
    public void markTask(Task taskToMark) {
        taskToMark.markAsDone();
    }

    @Override
    public String getMarkMessage() {
        return "Well done! I've marked this task as done:";
    }
}
