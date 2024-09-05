package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.Ui;

public class FindCommand extends Command {
    private String[] arguments;

    public FindCommand(String[] arguments) {
        super(false);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.findTasks(this.arguments);
    }
}
