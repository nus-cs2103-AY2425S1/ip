package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to search for tasks on a specific date.
 */
public class SearchCommand extends Command {
    private LocalDate searchDate;

    /**
     * Constructs a SearchCommand with the specified search date.
     *
     * @param searchDate The date to search for tasks on.
     */
    public  SearchCommand(LocalDate searchDate) {
        this.searchDate = searchDate;
    }

    /**
     * Displays all tasks on the specified date.
     *
     * @param tasks The task list that stores all tasks.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSearchList(tasks.getTasks(), searchDate);
    }
}
