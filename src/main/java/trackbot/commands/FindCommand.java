package trackbot.commands;

import java.util.List;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;
import trackbot.task.Task;
import trackbot.task.TrackList;
import trackbot.ui.Ui;

/**
 * Command to find task given keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(TrackList trackList, Ui ui, TrackBotStorage storage) throws TrackBotException {
        List<Task> listTasks = trackList.findTasks(keyword);
        return trackList.showFoundTasks(listTasks);
    }
}
