package hypebot.command;

import java.io.File;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import hypebot.main.HypeBot;
import hypebot.parser.command.CommandParser;
import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.cli.UiCli;
import hypebot.ui.cli.UiResponse;

/**
 * Represents the {@code GreetCommand} that searches for {@link Task}s in the {@link Tasklist}
 * of a {@link HypeBot} happening on a {@link LocalDate} search date.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'happening'}
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class HappeningCommand extends Command {
    /** {@link LocalDate} to search for {@link Task}s occurring. */
    private final LocalDate searchDate;

    /**
     * Takes in a {@link LocalDate} object representing a search date for {@link Task}s
     * happening on the given date and creates a new {@code HappeningCommand}.
     *
     * @param date {@link LocalDate} search date to search for {@link Task}s happening
     *             on the day in a {@link Tasklist}.
     */
    public HappeningCommand(LocalDate date) {
        super();
        searchDate = date;
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to create a new {@link Tasklist} of
     * any {@link Task}s occurring on the {@code searchDate}, then triggers {@link HypeBot}-associated
     * {@link UiCli} to return a {@link UiResponse} showing the new {@link Tasklist}.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} showing the new {@link Tasklist} containing {@link Task}s
     *         that happen on this {@code FindCommand}'s {@code searchDate}.
     * @throws NoSuchElementException If {@code Tasklist} is empty or there are no {@link Task}s that
     *      *                         occurring on the given date.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager)
            throws NoSuchElementException {
        Tasklist tasksHappening = tasks.getHappeningOn(searchDate);
        return uiCli.showTasksHappeningOnDate(searchDate, tasksHappening);
    }
}
