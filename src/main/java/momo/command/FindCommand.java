package momo.command;

import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Class for find command
 */
public class FindCommand extends Command {

    public static final int COMMAND_PREFIX_OFFSET = 4;

    /**
     * Runs the find command, filtering tasks in the list that contain the specified description and returns
     * a formatted string of matching tasks.
     * @param input User input including the keywords that should be found.
     * @param tasks TaskList containing all the user's added tasks.
     * @throws InvalidCommandException If the input is improperly formatted or the event task could not be created.
     */
    public static String run(String input, TaskList tasks) throws InvalidCommandException {
        assert tasks != null : "TaskList should not be null";

        String desc = input.substring(COMMAND_PREFIX_OFFSET).trim().toLowerCase();
        if (desc.isEmpty()) {
            throw new InvalidCommandException("What are you trying to find?! Don't make me come and find YOU...");
        }

        StringBuilder matchingTasks = getMatchingTasks(tasks, desc);

        if (matchingTasks.isEmpty()) {
            return "There are no matching tasks in your list";
        } else {
            return "Here are the matching tasks in your list:\n" + matchingTasks.toString();
        }
    }

    public static StringBuilder getMatchingTasks(TaskList tasks, String desc) {
        StringBuilder matchingTasks = new StringBuilder();

        int count = 1;

        for (Task task : tasks.getTaskList()) {
            if (task.getTask().toLowerCase().contains(desc)) {
                matchingTasks.append(count).append(". ").append(task).append("\n");
            }
            count++;
        }

        return matchingTasks;
    }
}
