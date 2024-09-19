package hypebot.command;

import java.io.File;
import java.io.IOException;

import hypebot.main.HypeBot;
import hypebot.parser.command.CommandParser;
import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.cli.UiCli;
import hypebot.ui.cli.UiErrorResponse;
import hypebot.ui.cli.UiResponse;

/**
 * Represents the {@code ByeCommand} created when user is ready to exit.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'bye'}
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see HypeBot
 * @see StorageManager
 * @see CommandParser
 */
public class ByeCommand extends Command {
    /**
     * Creates a new {@code ByeCommand}.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link StorageManager} to {@code save()}
     * the current {@link Tasklist} to the user's local computer, then triggers
     * the {@link HypeBot}-associated {@link UiCli} to return a {@link UiResponse}
     * that the {@link HypeBot}'s {@link Tasklist} are being saved to user.
     * <p>If {@link File} to save not found, triggers {@link UiCli} to return a
     * {@link UiErrorResponse}.</p>
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return Appropriate {@link UiResponse} depending on success of {@link Tasklist}
     *         encoding.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        try {
            storageManager.save(tasks);
            return uiCli.showSavingTasks();
        } catch (IOException e) {
            return uiCli.showError(e.getMessage());
        }
    }
}
