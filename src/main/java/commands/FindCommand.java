package commands;

import models.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand implements Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        List<Task> matched = new ArrayList<>();

        // Search through
        for (Task task : tasks) {
            if (task.getName().contains(this.query)) {
                matched.add(task);
            }
        }

        // Print results
        if (matched.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        System.out.println("Here's what I found:");
        for (int i = 0; i < matched.size(); i++) {
            Task task = matched.get(i);
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }
}
