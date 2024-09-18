package gavinchatbot.command;

import java.io.IOException;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

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
     * @return A message indicating the task has been successfully marked as done.
     * @throws GavinException If the task index is invalid or an error occurs during marking.
     * @throws IOException If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        try {
            Task task = tasks.getTask(index);
            tasks.markTask(index);
            storage.save(tasks.getTasks());
            return ui.showMarkedTask(tasks.getTask(index));
        } catch (GavinException e) {
            return "An error occurred: " + e.getMessage();
        } catch (IOException e) {
            return "An error occurred while saving: " + e.getMessage();
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
