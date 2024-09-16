package lolo.command;

import lolo.storage.Storage;
import lolo.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * This command returns the current list of tasks as a string
 * to be displayed in the UI.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by returning the current list of tasks as a string.
     * This command does not modify the task list or storage.
     *
     * @param tasks The list of tasks to be displayed.
     * @param storage The storage, which is not used by this command.
     * @return A string representation of the current task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "No tasks added yet.";
        }

        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }

        return result.toString();
    }
}

