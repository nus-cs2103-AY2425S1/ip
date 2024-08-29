package commands;

import models.Task;

import java.util.List;

public class ListCommand implements Command {
    private final List<Task> tasks;

    public ListCommand(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
}
