package gavinchatbot.command;

import gavinchatbot.task.Task;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;
import java.util.ArrayList;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        ui.showFoundTasks(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
