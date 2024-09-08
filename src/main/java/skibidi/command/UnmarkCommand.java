package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.TaskList.TaskNotFoundException;
import skibidi.Ui;
import skibidi.task.AbstractTask;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends AbstractCommand {
    private final int taskId;

    /**
     * Construct new unmark command for given task id.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Execute unmark command and return string to be printed.
     */
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
