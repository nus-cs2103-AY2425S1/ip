package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.task.Task;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

/**
 * An abstract class for all the commands in TrackBot.
 */
public abstract class Command {

    /**
     * Executes a command with provided trackList, ui and storage.
     *
     * @param trackList List to operate command on.
     * @param ui UI which interacts with user.
     * @param storage TrackBot storage to store data.
     * @throws TrackBotException If any error occurs while executing the command.
     */
    public abstract void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException;

    /**
     * Boolean function to exit TrackBot.
     *
     * @return true If user want to exit TrackBot.
     */
    public boolean isExit() {
        return false;
    }
}
