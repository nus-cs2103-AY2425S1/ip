package tars.commands;

import tars.tasks.TaskList;

import java.util.List;

/**
 * Represents a command to find tasks in the task list based on a description.
 *
 * <p>The {@code FindCommand} class extends the {@link Command} class and provides
 * functionality to search for tasks whose descriptions match a given input. It
 * generates a response containing all matching tasks.</p>
 */
public class FindCommand extends Command {

    /**
     * Executes the find command, searching for tasks that match the given input description.
     *
     * @param input The input string representing the search term or description to find in tasks.
     * @param tasks The {@link TaskList} object containing the tasks to be searched.
     * @return A {@link String} message listing all tasks that match the given description.
     */
    @Override
    public String execute(String input, TaskList tasks) {

        List<String> tasksFound = tasks.findTaskByDescription(input);

        StringBuilder str = new StringBuilder("Here are the tasks found.\n");

        for (String s : tasksFound) {
            str.append(s).append('\n');
        }

        if (!str.isEmpty()) {
            str.setLength(str.length() - 1);
        }

        return str.toString();
    }
}
