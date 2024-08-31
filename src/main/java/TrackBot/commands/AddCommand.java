package TrackBot.commands;

import TrackBot.task.Task;
import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

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
