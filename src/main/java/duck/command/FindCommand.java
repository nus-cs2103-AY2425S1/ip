package duck.command;

import duck.storage.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents a command to find a task from the task list.
 */
public class FindCommand implements Command {
    private final String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        assert this.fullCommand != null;
        String keyword = fullCommand.split(" ")[1];
        TaskList tasksWithKeyword = list.findTasks(keyword);
        ui.showTasks(tasksWithKeyword);
    }
}
