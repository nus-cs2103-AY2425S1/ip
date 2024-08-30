package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The index of the task to be unmarked as not done in the task list.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark command, marking the specified task as not done, displaying a message to the user,
     * and saving the updated task list to storage.
     *
     * @param tasks The task list containing the tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage system used to save the updated task list.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.unmarkTaskAsDone(taskNumber);
        ui.showTaskUnmarked(tasks.getTask(taskNumber));
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the unmark command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}