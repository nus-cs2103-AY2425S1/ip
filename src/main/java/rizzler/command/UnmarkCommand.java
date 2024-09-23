package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

/**
 * Represents user's command to unmark a task.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_FORMAT = """
            Correct Usage:
            unmark {task ID}
            Examples:
            unmark 3
            unmark 1""";
    private final int taskToUnmark;

    /**
     * Constructs an UnmarkCommand, given a task number they wish to unmark.
     * @param taskToUnmark Task number to be unmarked, as shown in <code>list</code>.
     */
    public UnmarkCommand(int taskToUnmark) {
        super();
        this.taskToUnmark = taskToUnmark;
    }

    /**
     * Unmarks the given task number. If the task number is not found in the taskLog,
     * an error message is printed instead.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Message updating the user on the result of the unmarking operation.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        try {
            Task unmarkedTask = taskLog.undoTask(taskToUnmark);
            storage.storeTasks(taskLog);
            return new String[] {"no worries, we'll circle back around to this.",
                    "\t" + unmarkedTask.toString()};
        } catch (RizzlerException e) {
            return new String[] {e.getMessage()};
        }
    }
}
