package nether.command;

import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * A command that searches for tasks in the task list that match the user's input string.
 * The search is case-insensitive and returns a list of all tasks that contain the input string.
 */
public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList searchResult;
        if (searchString.startsWith("#")) {
            searchResult = tasks.searchTag(searchString.substring(1).toLowerCase().trim());
            // search from 2nd character onwards
        } else {
            searchResult = tasks.searchTask(searchString);
        }
        return ui.printMatchingTasks(searchResult);
    }

}
