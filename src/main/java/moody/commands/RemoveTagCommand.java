package moody.commands;

import moody.exceptions.InvalidCommandException;
import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to remove a tag from a task.
 */
public class RemoveTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs a {@code RemoveTagCommand} with the specified task index and tag.
     *
     * @param taskIndex The index of the task to untag.
     * @param tag The tag to remove.
     */
    public RemoveTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidCommandException("Error: Task index out of bounds.\n");
        }

        Task task = tasks.get(taskIndex);
        if (task.removeTag(tag)) {
            storage.save(tasks.toArrayList()); // Save the updated list
            return "Tag removed: " + tag + "\n" + task;
        } else {
            return "Tag not found: " + tag;
        }
    }
}
