package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * Finds and display all matching tasks in TaskList.
 */
public class FindCommand extends Command {

    private final String str;

    public FindCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.find(tasks.findTask(this.str));
    }
}
