package momo.command;

import momo.Ui;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Class for find command
 */
public class FindCommand extends Command {

    public static final int COMMAND_PREFIX_OFFSET = 4;

    /**
     * Runs the find command
     * @param input
     * @param tasks
     * @throws InvalidCommandException
     */
    public static String run(String input, TaskList tasks) throws InvalidCommandException {
        assert tasks != null : "TaskList should not be null";

        StringBuilder matchingTasks = new StringBuilder();
        String desc = input.substring(COMMAND_PREFIX_OFFSET).trim().toLowerCase();

        if (desc.isEmpty()) {
            throw new InvalidCommandException("What are you trying to find?! Don't make me come and find you...");
        }

        int count = 1;
        for (Task task : tasks.getTaskList()) {
            if (task.getTask().toLowerCase().contains(desc)) {
                matchingTasks.append(count).append(". ").append(task).append("\n");
            }
            count++;
        }

        if (matchingTasks.isEmpty()) {
            return "There are no matching tasks in your list";
        } else {
            return "Here are the matching tasks in your list:" + matchingTasks.toString();
        }
    }
}
