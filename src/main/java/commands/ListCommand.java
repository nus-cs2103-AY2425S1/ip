package commands;

import models.Task;
import models.TaskList;

public class ListCommand implements Command {
    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            context.ui().showMessageF("%d. %s", i + 1, task);
        }
    }
}
