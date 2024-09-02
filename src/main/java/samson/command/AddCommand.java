package samson.command;// Samson.Samson.Samson.Samson.Command.Command.AddCommand.java
import java.io.IOException;

import samson.Storage;
import samson.Ui;
import samson.task.*;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList);
        storage.saveTasksToFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

