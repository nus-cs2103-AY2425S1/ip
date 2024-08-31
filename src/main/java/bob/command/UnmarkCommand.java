package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;
import bob.UI;

public class UnmarkCommand extends Command{

    private final int index;
    public UnmarkCommand(int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        taskList.unmarkTaskAtIndex(index);
        UI.printUnmarkTask();
    }
}
