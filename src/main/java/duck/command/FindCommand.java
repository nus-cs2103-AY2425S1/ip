package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to find a task from the task list.
 */
public class FindCommand implements Command {
    private final String fullCommand;

    /**
     * Constructs a FindCommand instance with the command input.
     *
     * @param fullCommand the full command string input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command to finds a task from the task list.
     * Updates the UI.
     *
     * @param list The task list on which the action is performed.
     * @param ui The UI to display information.
     * @param storage The storage for tasks.
     */
    @Override
    public void executeCommand(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand != null;
        String keyword = parseKeyword(fullCommand);
        TaskList tasksWithKeyword = list.findTasks(keyword);
        ui.showTasks(tasksWithKeyword);
    }

    private String parseKeyword(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new ArrayIndexOutOfBoundsException("Keyword not found.");
        }
        return parts[1];
    }
}
