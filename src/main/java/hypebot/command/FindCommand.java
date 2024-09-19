package hypebot.command;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import hypebot.main.HypeBot;
import hypebot.parser.command.CommandParser;
import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.cli.UiCli;
import hypebot.ui.cli.UiResponse;

/**
 * Represents the {@code FindCommand} that searches for {@link Task}s in the {@link Tasklist}
 * of a {@link HypeBot} matching a regex {@link Pattern} of keywords.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'find'}
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class FindCommand extends Command {
    /** Regex {@link Pattern} of search keywords. */
    private final Pattern searchQuery;

    /**
     * Takes in a search query in the form of a regex {@link Pattern} containing keywords
     * to find {@link Task}s with any keywords in its {@code name} in the
     * {@link HypeBot}-associated {@link Tasklist}, and creates a new {@code FindCommand}.
     *
     * @param searchQuery Regex {@link Pattern} search query containing keywords to
     *                    search for in each {@link Task}'s {@code name}.
     */
    public FindCommand(Pattern searchQuery) {
        super();
        this.searchQuery = searchQuery;
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to create a new {@link Tasklist} of
     * any {@link Task}s matching the {@code searchQuery}, then triggers {@link HypeBot}-associated
     * {@link UiCli} to return a {@link UiResponse} showing the new {@link Tasklist}.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} showing the new {@link Tasklist} containing {@link Task}s
     *         that match this {@code FindCommand}'s {@code searchQuery}.
     * @throws NoSuchElementException If {@code Tasklist} is empty or there are no {@link Task}s that
     *      *                         have a name with any of the keywords.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager)
            throws NoSuchElementException {
        Tasklist tasksWithSearchQuery = tasks.getNameContains(searchQuery);
        return uiCli.showTasksWithSearchQuery(String.valueOf(searchQuery), tasksWithSearchQuery);
    }
}
