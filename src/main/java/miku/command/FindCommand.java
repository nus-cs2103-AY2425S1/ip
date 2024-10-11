package miku.command;

import miku.utility.Response;
import miku.utility.Storage;
import miku.utility.TaskList;

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
    public void execute(TaskList taskList, Response ui, Storage storage) {
        ui.setResponse(ui.searchList(string, taskList));
    }
}
