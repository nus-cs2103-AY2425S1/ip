package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.TrackList;
import TrackBot.Ui;


public abstract class Command {
    public abstract void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException;

    public boolean isExit() {
        return false;
    }
}
