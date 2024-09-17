package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;
import hypebot.ui.UiResponse;

/**
 * Represents the AddCommand created whenever user prompts to add a Task to the Tasklist.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Takes in a Task to add to the Tasklist and creates a new AddCommand.
     *
     * @param task Task to add into HypeBot's Tasklist.
     */
    public AddCommand(Task task) {
        super();
        taskToAdd = task;
    }

    /**
     * Triggers Tasklist to add Task, then triggers UiCli to show message saying task was added.
     *
     * @param tasks          Tasklist containing Tasks.
     * @param uiCli          User interface that deals with text user interacts with.
     * @param storageManager StorageManager containing File where tasks are loaded / saved.
     */
    @Override
    public UiResponse execute(Tasklist tasks, UiCli uiCli, StorageManager storageManager) {
        tasks.add(taskToAdd);
        return uiCli.showAddedTask(taskToAdd, tasks);
    }
}
