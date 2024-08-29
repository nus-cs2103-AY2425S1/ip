package joe.commands;

import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;

public class MarkCommand extends Command {
    private TaskList taskList;
    private int index;

    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() throws InvalidIndexException {
        taskList.markDone(index);
    }
}
