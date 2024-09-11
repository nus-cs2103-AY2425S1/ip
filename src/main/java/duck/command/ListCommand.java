package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to list tasks from the task list.
 */
public class ListCommand implements Command {
    private final String fullCommand;

    /**
     * Constructs a ListCommand instance with the command input.
     *
     * @param fullCommand the full command string input
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand.equals("list");
        assert list.getSize() >= 0;
        ui.showTasks(list);
    }
}
