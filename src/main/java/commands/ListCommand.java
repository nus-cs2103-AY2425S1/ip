package commands;

import models.Task;
import models.TaskList;

public class ListCommand implements Command {
    private final TaskList items;

    public ListCommand(TaskList items) {
        this.items = items;
    }

    @Override
    public void execute() {
        for (int i = 0; i < items.size(); i++) {
            Task task = items.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
}
