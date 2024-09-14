package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.Task;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws TaskManagerException {
        try {
            this.index = Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new TaskManagerException("Error: Invalid task number.");
        }
    }

    @Override
    public String  execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskManagerException("Error: Invalid task number.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        String ret = ui.showTaskMarked(task);
        storage.save(tasks.getTasks());
        return ret;
    }
}
