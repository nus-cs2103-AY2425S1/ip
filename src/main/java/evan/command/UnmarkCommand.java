package evan.command;

import evan.exception.NoSuchTaskException;
import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

/**
 * Represents a command that the chatbot can execute to mark a task as uncompleted.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Instantiates an UnmarkCommand object.
     *
     * @param taskNumber Task number of the task to be marked as uncompleted.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        taskList.markTaskAsUndone(taskNumber);
        ui.showSuccess("Alright, I've marked task (" + taskNumber + ") as uncompleted.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
