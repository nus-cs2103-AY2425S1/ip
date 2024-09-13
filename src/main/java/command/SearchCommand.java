package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * This class is used to handle search commands
 */
public class SearchCommand extends Command {

    public SearchCommand(String description) {
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.searchTask(getDescription());
    }
}
