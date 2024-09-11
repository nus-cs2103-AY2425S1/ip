package command;

import main.Storage;
import main.TaskList;

/**
 * Represents a command to unmark a task in the TaskList.
 */
public class UnmarkCommand extends Command {
    private final String input;

    /**
     * Constructs an UnmarkCommand with the specified input.
     *
     * @param input The input string containing the task index to unmark.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the UnmarkCommand by unmarking the specified task in the TaskList
     * and updating the storage.
     *
     * @param tasks The TaskList to unmark the task from.
     * @param storage The Storage to update with the unmarked task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String[] parts = this.input.split(" ", 2);
        int unmarkNum = Integer.parseInt(parts[1]);
        return storage.unmarkTask(unmarkNum, tasks);
    }
}
