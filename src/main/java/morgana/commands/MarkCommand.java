package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_SUCCESS = """
            Nice!. I've marked this task as done:
            %d. %s
            """;

    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified index.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        Task task = tasks.updateTaskStatus(index, true);
        storage.save(tasks);
        return MESSAGE_SUCCESS.formatted(index + 1, task);
    }

    @Override
    public String getStyleClass() {
        return "marked-label";
    }
}
