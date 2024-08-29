package commands;

import java.io.IOException;

import data.TaskList;
import data.Storage;
import ui.Ui;

/**
 * Represents the command to list all Tasks
 */
public class ListCommand implements Command {
    private String searchStr = "";

    public ListCommand() {
        super();
    }

    /**
     * Constructor for ListCommand
     *
     * @param remaining keyword used to search for tasks to list
     */
    public ListCommand(String remaining) {
        searchStr = remaining;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (searchStr.isBlank()) {
            taskList.listAllTasks(ui); // list all tasks if no keyword
        } else {
            taskList.searchTasks(searchStr, ui); // list tasks based on given keyword
        }

        return true;
    }
}
