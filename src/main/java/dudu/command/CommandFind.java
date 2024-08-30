package dudu.command;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;
import dudu.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class CommandFind extends Command {
    String query;

    public CommandFind(String query) {
        this.query = query;
    }


    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ArrayList<Task> filteredTasks = taskList.findTasks(this.query);
        ui.findTasks(filteredTasks);
        storage.rewriteFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
