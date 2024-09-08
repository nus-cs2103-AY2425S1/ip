package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

public class DeleteCommand extends AbstractCommand {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            AbstractTask task = taskList.deleteTask(taskId);
            String message = String.format("DELETED TASK: %s\n", task.toString())
                    + String.format("NUMBER OF TASKS IN LIST: %d", taskList.size());
            return Optional.of(message);
        } catch (TaskNotFoundException err) {
            return Optional.of(err.getMessage());
        }
    }
}
