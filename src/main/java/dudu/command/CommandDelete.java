package dudu.command;

import dudu.command.Command;
import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

import java.io.IOException;

public class CommandDelete extends Command {
    int index;

    public CommandDelete(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        Task deletedTask = taskList.deleteTask(this.index);
        storage.rewriteFile(taskList);
        ui.deleteTask(deletedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
