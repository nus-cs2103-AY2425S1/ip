package mira.command;

import java.io.IOException;

import mira.Savable;
import mira.Storage;
import mira.Task;

/**
 * Represents a command to delete a task in the task list.
 */
public class DeleteCommand extends Command implements Savable {
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified index.
     *
     * @param index The index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting the task to the task list.
     *
     * @return A message indicating that the task has been added.
     */
    @Override
    public String execute() {
        Task removedTask = taskList.getTask(index);
        taskList.deleteTask(index);
        return "Noted. I've removed this task:\n  " + removedTask
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Delete the specific task in storage.
     *
     * @param storage The storage to delete the task to.
     * @throws IOException If there is an error in file operations.
     */
    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTasks(taskList.getTasks());
    }
}
