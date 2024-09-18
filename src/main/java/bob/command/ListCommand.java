package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ListCommand extends Command {

    public static final String COMMAND = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String argument) {
        if (tasks.isEmpty()) {
            ui.printWithFormat("You have not added any tasks yet.");
        } else {
            ui.printWithFormat(tasks.toString());
        }
    }
}
