package TrackBot.commands;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;
import TrackBot.task.Task;
import TrackBot.task.TrackList;
import TrackBot.ui.Ui;

import java.util.List;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        List<Task> listTasks = trackList.findTasks(keyword);
        trackList.showFoundTasks(listTasks);
    }
}
