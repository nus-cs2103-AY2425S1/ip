package mira.command;

import java.io.IOException;

import mira.Savable;
import mira.Storage;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command implements Savable {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} with the specified index of the task to be marked.
     *
     * @param index The index of the task to mark as done (1-based index).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the specified task as done.
     *
     * @return A message indicating that the task has been marked as done.
     */
    @Override
    public String execute() {
        taskList.markTask(index);
        return "Nice! I've marked this task as done:\n  " + taskList.getTask(index);
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
