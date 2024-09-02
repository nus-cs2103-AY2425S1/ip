package commands;

import parser.Parser;
import models.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand implements Command {
    private final List<Task> tasks;
    private final String query;

    public FindCommand(List<Task> tasks, String message) {
        this.tasks = tasks;

        String messageArgs = Parser.parseMessage(message).args();
        this.query = Parser.extractArgs(messageArgs, new String[]{})[0];
    }

    @Override
    public void execute() {
        List<Task> matched = new ArrayList<>();

        // Search through
        for (Task task : this.tasks) {
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
