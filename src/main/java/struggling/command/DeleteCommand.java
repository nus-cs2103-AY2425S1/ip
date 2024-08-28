package struggling.command;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ui.showDeleteTask(tasks.deleteTask(this.index), tasks.getSize());
    }
}
