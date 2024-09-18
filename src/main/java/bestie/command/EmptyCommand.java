package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class EmptyCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }
}
