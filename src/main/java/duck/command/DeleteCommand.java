package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;


/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final String fullCommand;

    /**
     * Constructs a DeleteCommand instance with the command input.
     *
     * @param fullCommand the full command string input.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command to delete a task from the task list.
     * Updates the UI and saves the changes to storage.
     *
     * @param list The task list on which the action is performed.
     * @param ui The UI to display information.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand != null;
        int taskIndex = parseTaskIndex(this.fullCommand);

        int initialSize = list.getSize();
        list.delete(taskIndex);
        int finalSize = list.getSize();
        assert initialSize - 1 == finalSize;

        ui.showNumOfTasksMessage(list);
        storage.saveTasks(list);
    }

    private int parseTaskIndex(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }
}
