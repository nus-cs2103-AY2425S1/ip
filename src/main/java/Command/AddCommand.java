package Command;

import Data.Task;
import Exceptions.NahException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class AddCommand extends Command{
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws NahException {
        tasks.add(this.task);
        ui.show(" Got it. I've added this task:\n"
                + "   " + this.task.toString() + "\n"
                + " Now you have " + tasks.taskCount() + " tasks in the list.\n");
        storage.rewrite(tasks.brief());
    }

}
