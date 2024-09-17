package hypebot.command;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * Represents the FindCommand created when user prompts 'find {keywords}'.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class FindCommand extends Command {
    private Pattern searchQuery;

    /**
     * Takes in a search query containing keywords to search for in HypeBot's Tasklist
     * and creates a new FindCommand.
     *
     * @param searchQuery Search query containing keywords to search for in the Task names.
     */
    public FindCommand(Pattern searchQuery) {
        super();
        this.searchQuery = searchQuery;
    }

    /**
     * Triggers Tasklist to return a new Tasklist containing Tasks with any of the
     * keywords in the search query, then triggers UiCli to output Tasks in this
     * Tasklist onto user interface.
     *
     * @param tasks Tasklist containing Tasks.
     * @param uiCli User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public String execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) throws NoSuchElementException {
        Tasklist tasksWithSearchQuery = tasks.getNameContains(searchQuery);
        return uiCli.showTasksWithSearchQuery(String.valueOf(searchQuery), tasksWithSearchQuery);
    }
}
