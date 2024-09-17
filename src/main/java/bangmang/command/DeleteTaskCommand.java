package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteTaskCommand with the specified task index.
     *
     * @param listIndex The task number of the task in the list to delete.
     */
    public DeleteTaskCommand(int listIndex) {
        this.taskIndex = listIndex - 1; // Convert to 0-based index
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(taskIndex);
            tasks.delete(taskIndex);
            storage.save(tasks.getTasks());
            return ui.showDeletedTask(task, tasks);

        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Alamak, task number out of range. Please provide a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
