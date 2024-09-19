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
 * Represents the base {@code Command} class which all {@code Command}s
 * parsed by the {@link CommandParser} inherit from.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see HypeBot
 * @see CommandParser
 * @see AddCommand
 * @see ByeCommand
 * @see DeleteCommand
 * @see DeleteAllCommand
 * @see FindCommand
 * @see GreetCommand
 * @see HappeningCommand
 * @see HelpCommand
 * @see ListCommand
 * @see MarkCommand
 * @see UnmarkCommand
 * @see UnknownCommand
 */
public abstract class Command {
    /**
     * Represents the base method implemented by all children of {@code Command}.
     * <p>Specifies manipulations on taken-in {@link Tasklist}, {@link UiCli}, and {@link StorageManager}
     * associated with a {@link HypeBot} according to type of {@code Command}.</p>
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File}
     *                       where {@link Task}s are loaded / saved.
     * @return The appropriate {@link UiResponse} according to type of {@code Command}.
     */
    public abstract UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager);
}
