package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
    private int taskNumber;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNumber The index of the task to be marked as done in the task list.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark command, marking the specified task as done, displaying a message to the user,
     * and saving the updated task list to storage.
     *
     * @param tasks The task list containing the tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage system used to save the updated task list.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.markTaskAsDone(taskNumber);
        ui.showTaskMarked(tasks.getTask(taskNumber));
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command will terminate the application.
     *
     * @return false, as the mark command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}