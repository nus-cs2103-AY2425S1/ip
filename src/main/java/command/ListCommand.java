package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * This class is used to handle list commands
 */
public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.list();
    }
}
