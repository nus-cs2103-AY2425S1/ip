package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * This class is used to handle miscellaneous commands
 */
public class MiscCommand extends Command {
    public MiscCommand(String description) {
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return super.getDescription();
    }
}
