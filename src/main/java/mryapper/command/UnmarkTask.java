package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;

/**
 * A command which marks the task as not done.
 */
public class UnmarkTask extends Command {
    private final int taskNumber;

    public UnmarkTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, StorageManager storage) {
        assert taskNumber > 0: "taskNumber should be greater than 0";
        assert taskNumber <= tasks.count(): "taskNumber should be less than or equal to number of tasks";

        String response;
        try {
            response = "OK, I've marked this task as not done yet:\n  "
                    + tasks.unmark(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            response = String.format("There is no such task!\n"
                    + "You currently have %d tasks in your list", tasks.count());
        }
        return response;
    }

    /**
     * Gets the syntax of the command.
     *
     * @return The syntax of the command to unmark command.
     */
    public static String getSyntax() {
        return "e.g. unmark 2";
    }
}
