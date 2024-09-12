package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_SUCCESS = """
            OK, I've marked this task as not done yet:
            %d. %s
            """;

    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} with the specified index.
     *
     * @param index The zero-based index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        Task task = tasks.updateTaskStatus(index, false);
        storage.save(tasks);
        return MESSAGE_SUCCESS.formatted(index + 1, task);
    }

    @Override
    public String getStyleClass() {
        return "add-label";
    }
}
