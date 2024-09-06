package trackbot.commands;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

public class InvalidCommand extends Command {
    private final String command;
    private final String correctFormat;

    public InvalidCommand(String command, String correctFormat) {
        this.command = command;
        this.correctFormat = correctFormat;
    }
    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        return "Invalid format for '" + command + "'. \nCorrect usage: " + correctFormat;
    }
}
