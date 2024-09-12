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
