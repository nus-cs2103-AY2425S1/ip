package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.ui.Ui;
import mummy.utility.Storage;

/**
 * Represents a command to find tasks based on a keyword.
 * Extends the Command class.
 */
public class FindCommand extends Command {

    public FindCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = this.getArgument("description");

        if (keyword == null) {
            ui.showError("Keyword must be provided");
            return;
        }

        ui.show(taskList
                .filter(task -> task.getDescription().contains(keyword))
                .toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
