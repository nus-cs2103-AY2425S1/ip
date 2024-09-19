package jeriel.command;

import jeriel.util.TaskList;
import jeriel.util.Storage;
import jeriel.util.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
