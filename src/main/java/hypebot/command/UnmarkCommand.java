package hypebot.command;

import java.io.File;

import hypebot.main.HypeBot;
import hypebot.parser.command.CommandParser;
import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.cli.UiCli;
import hypebot.ui.cli.UiResponse;

/**
 * Represents the {@code UnmarkCommand} that unmarks a {@link Task} from the {@link Tasklist}
 * of a {@link HypeBot} and deems it incomplete.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'unmark'},
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class UnmarkCommand extends Command {
    /** Index of {@link Task} to unmark and deem incomplete. */
    private final int indexOfTaskToUnmark;

    /**
     * Takes in an {@code int} index (0-indexed) indicating the position
     * of a {@link Task} to mark incomplete from the {@link Tasklist} and
     * creates a new {@code UnmarkCommand}.
     *
     * @param idx 0-indexed index of {@link Task} to mark incomplete from {@link Tasklist}.
     */
    public UnmarkCommand(int idx) {
        super();
        indexOfTaskToUnmark = idx;
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to {@code unmark()}
     * the {@link Task} with the chosen {@code indexOfTaskToUnmark}, then triggers
     * {@link HypeBot}-associated {@link UiCli} to return a {@link UiResponse}
     * showing that chosen {@link Task} was successfully marked incomplete.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} clarifying that chosen {@link Task} was successfully unmarked.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.unmark(indexOfTaskToUnmark);
        return uiCli.showUnmarkedTask(tasks.get(indexOfTaskToUnmark));
    }
}
