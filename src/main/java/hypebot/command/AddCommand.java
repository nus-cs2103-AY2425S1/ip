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
 * Represents the {@code AddCommand} created whenever user prompts to add a {@link Task}
 * to the {@link Tasklist} of a {@link HypeBot}.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'todo'},
 * {@code 'deadline'}, or {@code 'event'} command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class AddCommand extends Command {
    /** {@link Task} object to be added. */
    private final Task taskToAdd;

    /**
     * Takes in a {@link Task} to add to a {@link Tasklist}
     * and creates a new {@code AddCommand}.
     *
     * @param task {@link Task} to add.
     */
    public AddCommand(Task task) {
        super();
        taskToAdd = task;
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to add a {@link Task},
     * and returns a {@link UiResponse} from the {@link HypeBot}-associated {@link UiCli}
     * clarifying that the {@link Task} was successfully added.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} clarifying that the {@link Task} was successfully added.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.add(taskToAdd);
        return uiCli.showAddedTask(taskToAdd, tasks);
    }
}
