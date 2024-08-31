package bob.command;

import bob.UI;
import bob.exceptions.InvalidTaskNumberException;
import bob.tasks.Task;
import bob.tasks.TaskList;

public class FindCommand extends Command{

    private final String searchString;
    public FindCommand(String searchString) {
        super(true);
        this.searchString = searchString;
    }

    public void execute(TaskList taskList) throws InvalidTaskNumberException {
        UI.printMessage(taskList.filter(this.searchString));
    }
}
