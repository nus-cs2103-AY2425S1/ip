package TrackBot.commands;

import TrackBot.TrackBotStorage;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

/**
 * Command to exit TrackBot
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) {
        ui.showBye();
    }

    /**
     * Set boolean isExit to true to exit TrackBot
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}