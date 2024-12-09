package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;

/**
 * Represents a command by user to sort the list of tasks.
 */
public class SortCommand extends Command {
    private String sortBy;

    /**
     * Returns a SortCommand object that sorts the list of tasks by the specified criteria.
     *
     * @param sortBy Criteria to sort the list of tasks by
     */
    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Sorts the list of tasks by the specified criteria.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that interacts with the user.
     * @param storage The storage object.
     * @return List of tasks sorted by the specified criteria.
     * @throws PikappiException If the specified criteria is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        assert tasks != null : "TaskList cannot be null";
        try {
            tasks.sortTasks(sortBy);
        } catch (PikappiException e) {
            return ui.showSortOptions();
        }
        return tasks.listTasks();
    }
}
