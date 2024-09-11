package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

/**
 * The {@code FindCommand} class represents a command to find tasks in the task list based on a search query.
 */
public class FindCommand extends Command {
    private final String searchQuery;

    /**
     * Constructs a FindCommand object with the specified search query.
     *
     * @param searchQuery The query used to search for tasks.
     */
    public FindCommand(String searchQuery) {
        assert !searchQuery.isEmpty() : "Search query should not be empty";

        this.searchQuery = searchQuery;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return ui.showMessage("You currently have no tasks.");
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");

            // ui.showMessage("Here are the matching tasks in your list:");
            int j = 1;
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).toString().contains(searchQuery)) {
                    message.append(j).append(". ").append(tasks.getTask(i)).append("\n");
                    j++;
                }
            }

            return message.toString();
        }
    }
}
