package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Command to show correct usage.
 */
public class InvalidCommand extends Command {
    private final String command;
    private final String correctFormat;

    /**
     * Constructs an invalid command with the executed command and the correct format.
     * @param command Executed command.
     * @param correctFormat Show the correct format to user.
     */
    public InvalidCommand(String command, String correctFormat) {
        this.command = command;
        this.correctFormat = correctFormat;
    }
    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        return "Invalid format for '" + command + "'. \nCorrect usage: " + correctFormat;
    }
}
