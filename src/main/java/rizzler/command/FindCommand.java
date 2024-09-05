package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;

/**
 * Represents the user's command to find a task based on a search term.
 */
public class FindCommand extends Command {

    /**
     * Constructor for a FindCommand object.
     * @param strToMatch String that the user wants to search for within all tasks.
     */
    public FindCommand(String strToMatch) {
        super(strToMatch);
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        Task[] tasks = taskLog.getLog();
        StringBuilder output = new StringBuilder();
        int numMatches = 1;
        if (tasks.length == 0) {
            output.append("our list is empty right now dear, no tasks to search through!\n");
        } else {
            output.append("here are the tasks that match \"");
            output.append(getTextInput());
            output.append("\":\n");
            for (Task task : tasks) {
                if (!task.getDesc().contains(getTextInput())) {
                    continue;
                }
                output.append(numMatches);
                output.append(". ");
                output.append(task);
                output.append("\n");
                numMatches++;
            }
        }
        return output.toString().split("\n");
    }
}
