package commands;

import java.io.IOException;

import data.TaskList;
import data.Storage;
import ui.Ui;

public class ListCommand implements Command {
    private String searchStr = "";

    public ListCommand() {
        super();
    }

    public ListCommand(String remaining) {
        searchStr = remaining;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (searchStr.isBlank()) {
            taskList.listAllTasks(ui);
        } else {
            taskList.searchTasks(searchStr, ui);
        }

        return true;
    }
}
