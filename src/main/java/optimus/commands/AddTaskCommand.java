package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.tasks.Task;

/**
 * Command to add task to list
 */
public class AddTaskCommand extends Command {

    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to both permenant and session storage and prints success message
     *
     * @param storage - permenant storage
     * @param tasks   - session storage
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        tasks.addTask(task);
        storage.appendToStorage(task.getStorageString());
        return "Got it. I've added this task:\n" + task + "\n" + String.format("Now you have %d tasks in the list",
                tasks.getNumOfTasks());
    }

    public Task getTask() {
        return task;
    }
}
