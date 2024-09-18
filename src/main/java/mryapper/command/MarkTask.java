package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;

/**
 * A command which marks the task as done.
 */
public class MarkTask extends Command {
    public static final String SYNTAX = "e.g. mark 2";
    private final int taskNumber;

    public MarkTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, StorageManager storage) {
        assert taskNumber > 0: "taskNumber should be greater than 0";
        assert taskNumber <= tasks.count(): "taskNumber should be less than or equal to number of tasks";

        String response;
        try {
            response = "Nice! I have marked this task as done:\n  "
                    + tasks.mark(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            response = String.format("There is no such task!\n"
                    + "You currently have %d tasks in your list", tasks.count());
        }
        return response;
    }
}
