package niko.command;

import niko.common.NikoException;
import niko.main.Storage;
import niko.main.Ui;
import niko.task.TaskList;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as done (1-based).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, unmarking the task at the specified index as not done,
     * updating the UI, and saving the updated task list to storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save the updated task list.
     * @return
     * @throws NikoException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NikoException {
        tasks.unmarkTaskAsDone(index - 1);
        String readyToWrite = tasks.getTasks().toString();
        storage.write(readyToWrite.substring(1, readyToWrite.length() - 1));
        return ui.showUnmarkTaskMessage(tasks.getTask(index - 1));
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return {@code false}, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
