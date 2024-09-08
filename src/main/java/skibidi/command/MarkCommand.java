package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

public class MarkCommand extends AbstractCommand {
    private final int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            AbstractTask task = taskList.getTask(taskId);
            task.mark();
            String message = "MARKING TASK\n" + task.toString();
            return Optional.of(message);
        } catch (TaskNotFoundException err) {
            return Optional.of(err.getMessage());
        }
    }
}
