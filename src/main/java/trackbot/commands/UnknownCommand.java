package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Unknown Command.
 */
public class UnknownCommand extends Command {
    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        return "Sorry, I did not understand that command.";
    }
}
