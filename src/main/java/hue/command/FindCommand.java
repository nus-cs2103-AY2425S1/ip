package hue.command;
import hue.HueException;
import hue.UI.UI;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            String description = task.getDes
        }
    }
}
