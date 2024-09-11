package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

/**
 * The {@code ListCommand} class represents a command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command to list the tasks in the task list.
     *
     * @param tasks the task list on which the command operates
     * @param ui the user interface for interacting with the user
     * @param storage the storage for saving and loading tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "You currently have no tasks.";
        }

        StringBuilder message = new StringBuilder("Here is a list of all your tasks:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            message.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }

        return message.toString();
    }
}
