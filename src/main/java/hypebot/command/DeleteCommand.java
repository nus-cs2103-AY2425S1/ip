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
 * Represents the {@code DeleteCommand} that deletes a {@link Task} from the {@link Tasklist}
 * of a {@link HypeBot}.
 * <p>A child of {@link Command}.</p>
 * <p>Instantiated when the {@link CommandParser} successfully parses a {@code 'delete'},
 * command by the user.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see Tasklist
 * @see HypeBot
 * @see CommandParser
 */
public class DeleteCommand extends Command {
    /** Index of {@link Task} to delete. */
    private final int indexOfTaskToDelete;

    /**
     * Takes in an {@code int} index (0-indexed) indicating the position
     * of a {@link Task} to delete from the {@link Tasklist} and
     * creates a new {@code DeleteCommand}.
     *
     * @param idx 0-indexed index of {@link Task} to delete from {@link Tasklist}.
     */
    public DeleteCommand(int idx) {
        super();
        indexOfTaskToDelete = idx;
    }

    /**
     * Triggers the {@link HypeBot}-associated {@link Tasklist} to {@code delete()}
     * the {@link Task} with the chosen {@code indexOfTaskToDelete}, then triggers
     * {@link HypeBot}-associated {@link UiCli} to return a{@link UiResponse}
     * clarifying that chosen {@link Task} was successfully deleted.
     *
     * @param tasks          {@link Tasklist} containing {@link Task}s.
     * @param uiCli          {@link UiCli} that deals with text user interacts with.
     * @param storageManager {@link StorageManager} containing {@link File} where
     *                       {@link Task}s are loaded / saved.
     * @return {@link UiResponse} clarifying that chosen {@link Task} was successfully deleted.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        Task removedTask = tasks.remove(indexOfTaskToDelete);
        return uiCli.showDeletedTask(removedTask, tasks);
    }
}
