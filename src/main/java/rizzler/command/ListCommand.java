package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;

/**
 * Represents the user's request to list all tasks in the taskLog.
 */
public class ListCommand extends Command {
    public static final String EMPTY_LIST_RESPONSE = "our list is empty right now dear, no tasks to list!";
    public static final String TASK_LIST_HEADER = "these are the things we gotta do:";
    private static final int TASK_LIST_START_INDEX = 1;

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Iterates through every task in the taskLog and prints it out for the user.
     *
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Numbered lines representing each task.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        Task[] tasks = taskLog.getLog();
        StringBuilder output = new StringBuilder();
        if (tasks.length == 0) {
            return new String[] {EMPTY_LIST_RESPONSE};
        }

        output.append(TASK_LIST_HEADER).append("\n");
        for (int i = 0; i < tasks.length; i++) {
            output.append(i + TASK_LIST_START_INDEX).append(". ").append(tasks[i]);
            output.append("\n");
        }
        return output.toString().split("\n");
    }
}
