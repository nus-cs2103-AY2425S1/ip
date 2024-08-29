package derek.command;

import derek.Storage;
import derek.task.Task;
import derek.task.TaskList;
import derek.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }
    public void execute(int index, Storage storage, Ui ui) {
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        ui.removeTask(task);
    }
}
