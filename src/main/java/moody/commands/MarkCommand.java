package moody.commands;

import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a specific task as done.
 * This command updates the status of a task in the task list to completed.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     *
     * @param tasks The task list where the task to be marked is located.
     * @param ui The user interface for displaying the status of the marked task.
     * @param storage The storage to save the updated task list.
     * @throws IOException If an I/O error occurs while saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.save(tasks.toArrayList());
        ui.showMarkedTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
