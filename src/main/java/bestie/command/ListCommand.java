package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;

public class ListCommand extends Command {
    // display all the tasks in the list
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasks(tasks.getTasks());
    }

}
