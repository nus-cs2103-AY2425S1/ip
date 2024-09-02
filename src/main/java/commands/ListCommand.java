package commands;

import models.Task;

import java.util.List;

public class ListCommand implements Command {
    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
}
