package mummy.command;

import mummy.task.TaskList;
import mummy.ui.Ui;
import mummy.utility.Storage;

import java.util.HashMap;

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

        ui.show(taskList.filter(keyword).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
