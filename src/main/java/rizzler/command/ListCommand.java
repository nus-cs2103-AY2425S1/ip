package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;

/**
 * Represents the user's request to list all tasks in the taskLog.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Iterates through every task in the taskLog and prints it out for the user.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Numbered lines representing each task.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        Task[] tasks = taskLog.getLog();
        StringBuilder output = new StringBuilder();
        if (tasks.length == 0) {
            output.append("our list is empty right now dear, no tasks to list!\n");
        } else {
            output.append("these are the things we gotta do:\n");
            for (int i = 0; i < tasks.length; i++) {
                output.append(i + 1);
                output.append(". ");
                output.append(tasks[i]);
                output.append("\n");
            }
        }
        return output.toString().split("\n");
    }
}
