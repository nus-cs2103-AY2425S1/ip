package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

public class UnmarkCommand extends AbstractCommand {
    private final int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            AbstractTask task = taskList.getTask(taskId);
            task.unmark();
            String message = "UNMARKING TASK\n" + task.toString();
            return Optional.of(message);
        } catch (TaskNotFoundException err) {
            return Optional.of("" + err.getMessage());
        }
    }
}
