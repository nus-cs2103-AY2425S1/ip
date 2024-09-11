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
     * @param fullCommand the full command string input
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand != null;
        int taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;

        int initialSize = list.getSize();
        list.delete(taskIndex);
        int finalSize = list.getSize();
        assert initialSize - 1 == finalSize;

        ui.showNumOfTasksMessage(list);
        storage.saveTasks(list);
    }
}
