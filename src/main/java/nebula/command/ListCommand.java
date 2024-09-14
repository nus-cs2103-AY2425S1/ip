package nebula.command;

import nebula.storage.Storage;
import nebula.task.TaskList;
import nebula.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(ui.displayList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
