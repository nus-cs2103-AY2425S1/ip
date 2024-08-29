package joe.commands;

import joe.exceptions.InvalidIndexException;
import joe.tasks.TaskList;

public class UnmarkCommand extends Command {
    private final TaskList taskList;
    private final int index;

    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() throws InvalidIndexException {
        taskList.unmark(index);
    }
}
