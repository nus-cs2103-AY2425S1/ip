package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex Index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }
    @Override
    public String execute(TrackList taskList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        return taskList.unmarkTask(taskIndex);
    }
}
