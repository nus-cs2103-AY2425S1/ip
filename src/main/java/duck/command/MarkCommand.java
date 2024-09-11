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
     * @param fullCommand the full command string input
     */
    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand != null;
        int taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        list.markTask(taskIndex);
        ui.showMarkedTaskMessage(list.getTask(taskIndex));
        storage.saveTasks(list);
    }
}
