package mryapper.command;

import mryapper.storage.Storage;
import mryapper.task.TaskList;

/**
 * A command which marks the task as not done.
 */
public class UnmarkTask extends Command {
    public static final String SYNTAX = "e.g. unmark 2";
    private final int taskNumber;

    public UnmarkTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert taskNumber > 0: "taskNumber should be a positive integer";

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
}
