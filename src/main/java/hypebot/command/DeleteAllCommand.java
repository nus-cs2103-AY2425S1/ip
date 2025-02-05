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
 * Represents the {@code DeleteAllCommand} created that deletes all
 * {@link Task}s in a {@link HypeBot}-associated {@link Tasklist}.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'deleteall'}
 * command typed in by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class DeleteAllCommand extends Command {
    /**
     * Creates a new {@code DeleteAllCommand}.
     */
    public DeleteAllCommand() {
        super();
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to {@code deleteall()}
     * {@link Task}s, then triggers {@link HypeBot}-associated {@link UiCli} to return
     * a {@link UiResponse} clarifying that all {@link Task}s were deleted.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} clarifying that all {@link Task}s were successfully deleted.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.clear();
        return uiCli.showDeletedAllTasks();
    }
}
