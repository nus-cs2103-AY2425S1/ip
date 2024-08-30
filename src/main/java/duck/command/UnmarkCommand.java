package duck.command;

import duck.task.TaskList;
import duck.ui.Ui;
import duck.storage.Storage;

public class UnmarkCommand implements Command {
    private final String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        int taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        list.unmarkTask(taskIndex);
        ui.showUnmarkedTaskMessage(list.getTask(taskIndex));
        storage.saveTasks(list);
    }
}