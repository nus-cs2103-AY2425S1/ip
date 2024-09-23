package rizzler.command;

import rizzler.RizzlerException;
import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;

/**
 * Represents the user's command to mark a task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_FORMAT = """
            Correct Usage:
            mark {task ID}
            Examples:
            mark 3
            mark 1""";
    private final int taskToMark;

    /**
     * Constructs a <code>MarkCommand</code>.
     *
     * @param taskToMark The number of the task to mark, as shown in <code>list</code>.
     */
    public MarkCommand(int taskToMark) {
        super();
        this.taskToMark = taskToMark;
    }

    /**
     * Marks the given task as completed in taskLog. If no such task exists, an error message is printed.
     *
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Strings representing the outcome of attempting to mark the task.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        try {
            Task markedTask = taskLog.doTask(taskToMark);
            storage.storeTasks(taskLog);
            return new String[] {"aight, i'll note that you've completed this.",
                    "\t" + markedTask};
        } catch (RizzlerException e) {
            return new String[] {e.getMessage()};
        }
    }
}
