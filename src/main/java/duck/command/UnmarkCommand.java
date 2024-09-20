package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to unmark a task from the task list.
 */
public class UnmarkCommand implements Command {
    private final String fullCommand;

    /**
     * Constructs an UnmarkCommand instance with the command input.
     *
     * @param fullCommand the full command string input.
     */
    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command to unmark the specified task.
     * Updates the UI and saves the changes to storage.
     *
     * @param list The task list on which the action is performed.
     * @param ui The UI to display information.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        int taskIndex = parseTaskIndex(this.fullCommand);
        list.unmarkTask(taskIndex);
        ui.showUnmarkedTaskMessage(list.getTask(taskIndex));
        storage.saveTasks(list);
    }

    private int parseTaskIndex(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }
}
