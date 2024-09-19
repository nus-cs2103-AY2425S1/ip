package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;

/**
 * Represents a command to remove a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_SUCCESS = """
            Noted. I've removed this task:
            %d. %s
            Now you have %d task%s in the list.
            """;

    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified index.
     *
     * @param index The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws MorganaException {
        Task task = tasks.remove(index);
        storage.save(tasks);
        return MESSAGE_SUCCESS.formatted(
                index + 1, task, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    @Override
    public String getStyleClass() {
        return "delete-label";
    }
}
