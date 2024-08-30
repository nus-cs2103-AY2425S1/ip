package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command, removing the specified task from the task list,
     * displaying a message, and saving the updated task list to storage.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.showTaskRemoved(removedTask, tasks.getSize());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the delete command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}