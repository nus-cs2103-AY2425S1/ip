package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to mark a task from the task list.
 */
public class MarkCommand implements Command {
    private final String fullCommand;

    /**
     * Constructs a MarkCommand instance with the command input.
     *
     * @param fullCommand the full command string input.
     */
    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command to mark the specified task.
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
        list.markTask(taskIndex);

        ui.showMarkedTaskMessage(list.getTask(taskIndex));
        storage.saveTasks(list);
    }

    private int parseTaskIndex(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }
}
