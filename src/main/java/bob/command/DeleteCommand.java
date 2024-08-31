package bob.command;

import bob.exceptions.InvalidTaskNumberException;

import bob.tasks.Task;
import bob.tasks.TaskList;

import bob.UI;

public class DeleteCommand extends Command{

    private final int index;

    public DeleteCommand(int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        Task delTask = taskList.removeTaskAtIndex(this.index);
        UI.printDeleteTask(delTask);
        UI.printCurrentTaskListStatus(taskList);
    }
}
