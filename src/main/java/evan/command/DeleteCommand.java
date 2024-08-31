package evan.command;

import evan.exception.NoSuchTaskException;
import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

/**
 * Represents a command that the chatbot can execute to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates a DeleteCommand object.
     *
     * @param taskNumber Task number of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        taskList.deleteTask(taskNumber);
        ui.showSuccess("Okay, task (" + taskNumber + ") has been deleted.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
