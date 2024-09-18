package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.Task;
import mryapper.task.TaskList;

/**
 * A command which deletes the task from the task list.
 */
public class DeleteTask extends Command {
    private final int taskNumber;

    public DeleteTask(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, StorageManager storage) {
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

    /**
     * Gets the syntax of the command.
     *
     * @return The syntax of the command to delete command.
     */
    public static String getSyntax() {
        return "e.g. delete 2";
    }
}
