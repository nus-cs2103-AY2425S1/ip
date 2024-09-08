package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;
import skibidi.task.AbstractTask;

/**
 * Command to add an item to the task list.
 */
public class AddCommand extends AbstractCommand {
    private final AbstractTask task;
    private final String message;

    /**
     * Construct new add task command and message to log on command success.
     */
    public AddCommand(AbstractTask task, String message) {
        this.task = task;
        this.message = message;
    }

    /**
     * Execute add command and return string to be printed.
     */
    public Optional<String> execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        return Optional.of(message + String.format("NUMBER OF TASKS IN LIST: %d", taskList.size()));
    }
}
