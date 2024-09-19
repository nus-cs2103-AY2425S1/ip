package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.Task;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws TaskManagerException {
        try {
            this.index = Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new TaskManagerException("Error: Invalid task number.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        assert index >= 0 && index < tasks.size() : "Task index out of bounds";
        if (index < 0 || index >= tasks.size()) {
            throw new TaskManagerException("Error: Invalid task number.");
        }
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        String ret = ui.showTaskDeleted(task, tasks.size());
        storage.save(tasks.getTasks());
        return ret;
    }
}
