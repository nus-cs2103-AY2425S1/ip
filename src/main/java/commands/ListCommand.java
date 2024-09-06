package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Provides user input, listing available tasks and their state.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Execution actions:
     * - Convert all tasks to their string representations
     * - Print to user
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.length() == 0) {
            return "There are no tasks in the list.";
        }

        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.length(); i++) {
            listOfTasks
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.getTaskAt(i))
                    .append("\n");
        }

        // remove the last character, which is a new line.
        return listOfTasks.substring(0, Math.max(0, listOfTasks.length() - 1));
    }
}
