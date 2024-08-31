package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.task.Task;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public int getTaskIndex() {
        return taskIndex;
    }
    @Override
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        trackList.deleteFromList(taskIndex);
    }
}
