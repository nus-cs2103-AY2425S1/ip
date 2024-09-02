package duck.command;

import duck.task.TaskList;
import duck.ui.Ui;
import duck.storage.Storage;

public class FindCommand implements Command {
    private final String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        String keyword = fullCommand.split(" ")[1];
        TaskList tasksWithKeyword = list.findTasks(keyword);
        ui.showTasks(tasksWithKeyword);
    }
}
