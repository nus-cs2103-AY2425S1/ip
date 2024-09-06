package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Executable actions:
 * - Find all tasks that contain the search query as a substring
 * - Format the tasks traditionally
 * - Print remaining task list to the user
 */
public class FindCommand extends Command {
    private final String searchQuery;

    /**
     * Searches for all tasks which fulfil a given search query.
     * @param searchQuery current search query only
     */
    public FindCommand(String searchQuery) {
        super();
        this.searchQuery = searchQuery;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.length() == 0) {
            return "There are no tasks in the list.";
        }

        StringBuilder listOfTasks = new StringBuilder();
        listOfTasks.append("Here are the matching tasks for '" + searchQuery + "' in your list:\n");

        int counter = 1;
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.getTaskAt(i);
            System.out.println(task.getDescription());
            System.out.println(task.getDescription().contains("z"));
            if (task.getDescription().contains(searchQuery)) {
                listOfTasks
                        .append(counter)
                        .append(". ")
                        .append(tasks.getTaskAt(i))
                        .append("\n");
                counter++;
            }
        }

        // remove the last character, which is a new line.
        return listOfTasks.substring(0, Math.max(0, listOfTasks.length() - 1));
    }
}
