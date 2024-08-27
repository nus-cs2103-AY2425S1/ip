package strand.Commands;

import strand.Storage;
import strand.TaskList;
import strand.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);
    }
}
