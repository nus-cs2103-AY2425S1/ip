package barry.command;

import barry.task.Task;
import barry.util.Storage;
import barry.util.TaskList;
import barry.util.Ui;
import java.io.IOException;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command.
     * Marks the task as done, displays the updated task to the user,
     * and saves the updated task list to storage.
     *
     * @param allTasks The list of all tasks.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for saving updated tasks list.
     * @throws IOException If there is an error during input or output operations.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        try {
            Task task = allTasks.getTask(index);
            task.markAsDone();
            ui.showMark(task);
            storage.save(allTasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError(" Oh no... This is an invalid task number!");;
        }
    }
}
