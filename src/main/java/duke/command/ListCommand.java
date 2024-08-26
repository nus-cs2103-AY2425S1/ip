package duke.command;

import java.io.IOException;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasksList(); // Get the list of tasks from TaskList
        ui.showTasks(taskList); // Use the UI to show the tasks
    }

    @Override
    public boolean isExit() {
        return false;
    }
}  
