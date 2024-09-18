package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * An abstract class for all the commands in trackbot.
 */
public abstract class Command {

    /**
     * Executes a command with provided trackList, ui and storage.
     *
     * @param trackList List to operate command on.
     * @param ui UI which interacts with user.
     * @param storage trackbot storage to store data.
     * @throws TrackBotException If any error occurs while executing the command.
     */
    public abstract String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException;

    /**
     * Changes isExit for trackbot to exit.
     *
     * @return true If user want to exit trackbot.
     */
    public boolean isExit() {
        return false;
    }
}
