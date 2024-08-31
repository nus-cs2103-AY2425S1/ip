package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.TaskList;
import bob.UI;
public class MarkCommand extends Command{

    private final int index;
    public MarkCommand(int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        taskList.markTaskAtIndex(index);
        UI.printMarkTask();
    }
}
