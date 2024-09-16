package moody.commands;

import moody.exceptions.InvalidCommandException;
import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a tag to a task.
 */
public class AddTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs an {@code AddTagCommand} with the specified task index and tag.
     *
     * @param taskIndex The index of the task to tag.
     * @param tag The tag to add.
     */
    public AddTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Executes the AddTagCommand by adding the specified tag to the task at the given index.
     * This updates the task list and saves the modified list to storage.
     *
     * @param tasks The list of tasks where the tag will be added.
     * @param ui The user interface (not used in this method).
     * @param storage The storage to save the updated task list.
     * @return A confirmation message that the tag was successfully added to the task.
     * @throws InvalidCommandException If the task index is invalid or out of bounds.
     * @throws IOException If an I/O error occurs during saving.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidCommandException("Error: Task index out of bounds.\n");
        }
        Task task = tasks.get(taskIndex);
        task.addTag(tag);
        storage.save(tasks.toArrayList());
        return "Tag added: " + tag + "\n" + task;
    }
}
