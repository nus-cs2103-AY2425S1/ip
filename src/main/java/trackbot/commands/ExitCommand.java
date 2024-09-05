package trackbot.commands;

import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Command to exit trackbot
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) {
        return ui.showBye();
    }

    /**
     * Set boolean isExit to true to exit trackbot
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
