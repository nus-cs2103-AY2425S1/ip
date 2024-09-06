package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.Task;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an add command with a task.
     *
     * @param task Task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        return trackList.addToList(task);
    }
}
