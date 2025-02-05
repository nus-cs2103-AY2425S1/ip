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
 * Represents the {@code MarkCommand} that marks a {@link Task} from the {@link Tasklist}
 * of a {@link HypeBot} as completed.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'mark'},
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class MarkCommand extends Command {
    /** Index of {@link Task} to mark complete. */
    private final int indexOfTaskToMark;

    /**
     * Takes in an {@code int} index (0-indexed) indicating the position
     * of a {@link Task} to mark complete from the {@link Tasklist} and
     * creates a new {@code MarkCommand}.
     *
     * @param idx 0-indexed index of {@link Task} to mark complete from {@link Tasklist}.
     */
    public MarkCommand(int idx) {
        super();
        indexOfTaskToMark = idx;
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to {@code mark()}
     * the {@link Task} with the chosen {@code indexOfTaskToMark}, then triggers
     * {@link HypeBot}-associated {@link UiCli} to return a {@link UiResponse}
     * showing that chosen {@link Task} was successfully marked complete.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} clarifying that chosen {@link Task} was successfully marked.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.mark(indexOfTaskToMark);
        return uiCli.showMarkedTask(tasks.get(indexOfTaskToMark));
    }
}
