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
 * Represents the {@code UnmarkCommand} created when no proper keyword is entered.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} cannot successfully parse any
 * {@link Command} from user entry.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see HypeBot
 * @see CommandParser
 */
public class UnknownCommand extends Command {
    /** User-entered command word that did not correspond to any known {@link Command}. */
    private final String unknownCommand;

    /**
     * Takes in the unknown command keyword and creates a new {@code UnknownCommand}.
     *
     * @param command Unknown command keyword entered by user, from {@link CommandParser}.
     */
    public UnknownCommand(String command) {
        super();
        unknownCommand = command;
    }

    /**
     * Triggers the {@link HypeBot}-associated{@link UiCli} to return a {@link UiResponse}
     * showing the unknown-command message.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} showing the unknown-command message.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        return uiCli.showUnknownCommand(unknownCommand);
    }
}
