package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.TrackList;
import TrackBot.Ui;


public class ListCommand extends Command {
    @Override
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        trackList.printList();
    }
}
