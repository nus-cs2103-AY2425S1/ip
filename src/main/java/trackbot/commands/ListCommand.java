package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        trackList.printList();
    }
}
