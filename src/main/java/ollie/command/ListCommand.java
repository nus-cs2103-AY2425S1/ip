package ollie.command;

import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printResponse(tasks.toString());
    }
}
