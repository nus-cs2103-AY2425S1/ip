package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

/**
 * Class representing the find command.
 */
public class Find extends Command {
    private final String input;

    public Find(String input) {
        this.input = input;
    }

    private String findTasks(TaskList list) {
        StringBuilder matchingTasks = new StringBuilder();
        int count = 0;

        for (Task task : list) {
            if (task.getDescription().contains(input)) {
                matchingTasks.append(++count).append(". ").append(task).append("\n");
            }
        }

        if (count == 0) {
            return "";
        }

        return matchingTasks.toString().trim();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String matchingTasks = findTasks(list);

        if (matchingTasks.isEmpty()) {
            return "No tasks match your search.";
        }

        return "Here are the matching tasks in your list:\n" + findTasks(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
