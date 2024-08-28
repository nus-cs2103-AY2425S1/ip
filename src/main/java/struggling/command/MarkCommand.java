package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMarkTask(tasks.markTask(this.index));
    }
}
