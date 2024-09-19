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
 * Represents the {@code GreetCommand} created to simulate the start of a {@link HypeBot}
 * interaction.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'start'}
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see HypeBot
 * @see CommandParser
 */
public class GreetCommand extends Command {
    /**
     * Creates a new {@code GreetCommand};
     */
    public GreetCommand() {
        super();
    }

    /**
     * Triggers the {@link HypeBot}-associated{@link UiCli} to return a {@link UiResponse}
     * showing the greeting message.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} showing the greeting message.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        return uiCli.showGreeting();
    }
}
