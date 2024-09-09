package llama.commands;

import java.io.IOException;

import llama.data.Storage;
import llama.data.TaskList;
import llama.ui.Ui;

/**
 * Represents the command to list all Tasks
 */
public class ListCommand implements Command {
    private String searchStr = "";

    public ListCommand() {
        super();
    }

    /**
     * Creates the command to list out all tasks
     *
     * @param remaining keyword used to search for tasks to list
     */
    public ListCommand(String remaining) {
        searchStr = remaining;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String response = "";
        if (searchStr.isBlank()) {
            response = taskList.listAllTasks(ui); // list all tasks if no keyword
        } else {
            response = taskList.searchTasks(searchStr, ui); // list tasks based on given keyword
        }

        return response;
    }
}
