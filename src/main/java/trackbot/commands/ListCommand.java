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
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        return trackList.printList();
    }
}
