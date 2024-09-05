package nether.command;

import nether.storage.Storage;
import nether.Ui;
import nether.task.Task;
import nether.task.TaskList;

public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList searchResult = tasks.searchTask(searchString);
        return ui.printMatchingTasks(searchResult);
    }

}
