package TrackBot.commands;

import TrackBot.task.Task;
import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

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
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        trackList.addToList(task);
    }
}
