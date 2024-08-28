package mira.command;

import java.io.IOException;

import mira.Savable;
import mira.Storage;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class UnmarkCommand extends Command implements Savable {
    private final int index;

    /**
     * Constructs a {@code UnmarkCommand} with the specified index of the task to be marked.
     *
     * @param index The index of the task to mark as undone (1-based index).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by unmarking the specified task as done.
     *
     * @return A message indicating that the task has been marked as undone.
     */
    @Override
    public String execute() {
        taskList.unmarkTask(index);
        return "OK, I've marked this task as not done yet:\n  " + taskList.getTask(index);
    }

    /**
     * Saves the updated task list to the specified storage.
     *
     * @param storage The storage to save the updated task list.
     * @throws IOException If there is an error in file operations.
     */
    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTasks(taskList.getTasks());
    }
}
