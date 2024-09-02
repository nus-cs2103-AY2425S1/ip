package miku.command;

import miku.utility.Storage;
import miku.utility.TaskList;
import miku.utility.UI;

/**
 * Finds and prints the matched results
 */
public class FindCommand extends Command {
    private TaskList results = new TaskList();
    private String string = "";

    public FindCommand(String string) {
        this.string = string;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.searchList(string);
    }
}
