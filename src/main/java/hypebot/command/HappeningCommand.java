package hypebot.command;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * Represents the UnmarkCommand created when user prompts 'happening /{YYYY-MM-DD}'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class HappeningCommand extends Command {
    private LocalDate searchDate;

    /**
     * Takes in a LocalDate object representing a date that user is searching tasks for
     * and creates a new HappeningCommand.
     *
     * @param date Date user wants to look up tasks for in Tasklist.
     */
    public HappeningCommand(LocalDate date) {
        super();
        searchDate = date;
    }

    /**
     * Triggers Tasklist to return a new Tasklist only containing tasks happening on the given
     * date, then triggers UiCli to output Tasks in Tasklist onto user interface.
     *
     * @param tasks Tasklist containing Tasks.
     * @param uiCli User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public String execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) throws NoSuchElementException {
        Tasklist tasksHappening = tasks.getHappeningOn(searchDate);
        return uiCli.showTasksHappeningOnDate(searchDate, tasksHappening);
    }
}
