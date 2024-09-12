package moody.commands;

import moody.exceptions.InvalidCommandException;
import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, IOException {
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        if (keyword.trim().isEmpty()) {
            throw new InvalidCommandException("Error: The search keyword cannot be empty.\n");
        }

        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.toArrayList()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");

        if (matchingTasks.isEmpty()) {
            ui.showError("No tasks found with keyword: " + keyword);
            ui.showErrorAsString("No tasks found with keyword: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
                response.append(i + 1)
                        .append(". ")
                        .append(matchingTasks.get(i))
                        .append("\n");

            }
            ui.showLine();
            response.append(ui.showLineAsString());
        }
        return response.toString();
    }
}
