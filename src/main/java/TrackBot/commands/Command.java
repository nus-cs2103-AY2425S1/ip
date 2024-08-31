package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.task.Task;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

public abstract class Command {
    public abstract void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException;

    public boolean isExit() {
        return false;
    }
}
