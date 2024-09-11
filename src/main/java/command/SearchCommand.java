package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

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
     * Displays the tasks on the specified date to the user on the CLI,
     * and does not save the task list to file.
     *
     * @param tasks The task list to be searched by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSearchList(tasks.getTasks(), searchDate);

    }

    /**
     * Displays the tasks on the specified date to the user on the GUI,
     * and does not save the task list to file.
     *
     * @param tasks The task list to be searched by the command.
     * @param gui The GUI to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @return The list of tasks on the specified date.
     */
    @Override
    public String executeGui(TaskList tasks,UiGui gui, Storage storage) {
        return gui.showSearchList(tasks.getTasks(), searchDate);
    }
}
