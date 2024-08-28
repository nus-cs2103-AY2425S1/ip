package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.TrackList;
import TrackBot.Ui;
import TrackBot.commands.Command;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        trackList.deleteFromList(taskIndex);
    }
}
