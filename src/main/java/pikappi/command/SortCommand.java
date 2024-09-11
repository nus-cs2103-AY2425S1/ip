package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;

public class SortCommand extends Command {
    private String sortBy;

    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

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
