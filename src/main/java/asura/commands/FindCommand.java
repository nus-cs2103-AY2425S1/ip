package asura.commands;

import java.util.List;

import asura.data.tasks.Task;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

public class FindCommand extends Command {
    String description;
    public FindCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> filtered = taskList.find(description);
        TaskList temp = new TaskList(filtered);
        ui.printString(temp.toString());
    }

    public boolean isExit() {
        return false;
    }
}
