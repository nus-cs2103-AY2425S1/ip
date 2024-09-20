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
     * @param fullCommand the full command string input.
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command to list tasks from the task list.
     * Updates the UI.
     *
     * @param list The task list on which the action is performed.
     * @param ui The UI to display information.
     * @param storage The storage for tasks.
     */
    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand.equals("list");
        assert list.getSize() >= 0;
        ui.showTasks(list);
    }
}
