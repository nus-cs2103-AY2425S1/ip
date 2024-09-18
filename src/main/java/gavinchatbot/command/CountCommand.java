package gavinchatbot.command;

import java.io.IOException;

import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * Represents a command to count the number of tasks that are done.
 */
public class CountCommand implements Command {

    /**
     * Executes the count command, which counts and displays the number of done tasks.
     *
     * @param tasks The list of tasks.
     * @param ui The UI to display output.
     * @param storage The storage used to save tasks (not used in this command).
     * @throws GavinException If there is an issue during execution.
     * @throws IOException If there is an issue with storage (not used here but necessary for interface compliance).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException {
        long doneCount = tasks.countDoneTasks();
        String message = "Number of tasks marked as done: " + doneCount;
        return ui.showDoneCount(message);
    }

    /**
     * Indicates whether this command will cause the application to exit.
     *
     * @return false because the count command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
