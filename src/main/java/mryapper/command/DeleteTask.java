package mryapper.command;

import mryapper.storage.Storage;
import mryapper.task.Task;
import mryapper.task.TaskList;

/**
 * A command which deletes the task from the task list.
 */
public class DeleteTask extends Command {
    public static final String SYNTAX = "e.g. delete 2";
    private final int taskNumber;

    public DeleteTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert taskNumber > 0: "taskNumber should be greater than 0";

        String response;
        try {
            Task deletedTask = tasks.remove(taskNumber);
            tasks.saveToStorage(storage);
            response = String.format("Task deleted successfully!\n  %s\n"
                    + "Now you have %d tasks in the list", deletedTask, tasks.count());
        } catch (IndexOutOfBoundsException e) {
            response = String.format("There is no such task!\n"
                    + "You currently have %d tasks in your list", tasks.count());
        }
        return response;
    }
}
