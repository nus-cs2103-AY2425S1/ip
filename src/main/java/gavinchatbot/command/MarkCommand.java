package gavinchatbot.command;

import java.io.IOException;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking the task as done in the task list.
     *
     * @param tasks The task list containing the task to be marked.
     * @param ui The UI that will display the marked task.
     * @param storage The storage that will save the updated task list.
     * @throws GavinException If the task index is invalid or an error occurs during marking.
     * @throws IOException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        try {
            tasks.markTask(index);
            ui.showMarkedTask(tasks.getTask(index));
            storage.save(tasks.getTasks());
        } catch (GavinException e) {
            ui.showError("An error occurred: " + e.getMessage());
        } catch (IOException e) {
            ui.showError("An error occurred while saving: " + e.getMessage());
        }
    }

    /**
     * Returns whether the command causes the application to exit.
     *
     * @return false as the mark command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
