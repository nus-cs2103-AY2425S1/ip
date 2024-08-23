package myapp.command;

import myapp.core.BingBongUI;
import myapp.core.Storage;
import myapp.task.TaskList;
import myapp.task.Task;

public class DeleteCommand extends Command {

    int index;
    public DeleteCommand(int i) {
        super();
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, BingBongUI ui, Storage storage) throws  IndexOutOfBoundsException{
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.delete(index);
        saveTasks(tasks, ui, storage);
        ui.showResponse("Noted. I've removed this task:\n" + task
                + "\n" + "Now you have " + tasks.size()
                + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
