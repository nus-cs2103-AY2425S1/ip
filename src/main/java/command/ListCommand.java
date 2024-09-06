package command;

import main.TaskList;
import main.Storage;
import main.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.list();
    }
}
