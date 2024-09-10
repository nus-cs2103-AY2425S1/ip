package kobe.command;

import kobe.util.Storage;
import kobe.task.TaskList;
import kobe.util.Ui;

/**
 * Represents a command to list all tasks in the task list of the Duke chatbot application.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, which displays all tasks currently in the task list.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The Ui object responsible for user interactions.
     * @param storage The Storage object responsible for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.setResponse("Your task list is currently empty.");
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
            }
            ui.setResponse(sb.toString());
        }
    }
}
