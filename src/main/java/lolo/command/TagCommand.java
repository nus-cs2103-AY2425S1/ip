package lolo.command;

import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.Task;
import lolo.task.TaskList;

/**
 * Represents a command to add a tag to a task in the task list.
 * This command interacts with the user interface to confirm the addition
 * of a tag and updates the storage with the modified task list.
 */
public class TagCommand extends Command {
    private int taskIndex;
    private String tag;

    /**
     * Constructs a TagCommand with the specified task index and tag.
     *
     * @param taskIndex The index of the task to be tagged.
     * @param tag The tag to be added to the task.
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Executes the command by adding the tag to the specified task in the task list,
     * displaying a confirmation message to the user, and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to be modified by the command.
     * @param storage The storage to save the updated task list.
     * @return A string message confirming the addition of the tag.
     * @throws LoloException If the task index is invalid or there is an error during execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws LoloException {
        // Ensure taskIndex is within valid range
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            int indexShown = taskIndex + 1;
            throw new LoloException("Invalid task index: " + indexShown + " is out of Range");
        }
        // Retrieve the task from the task list
        Task task = tasks.get(taskIndex);

        // Add the tag to the task
        task.addTag(tag);

        // Save the updated task list to storage
        storage.save(tasks.getTasks());

        // Return confirmation message
        return "Got it. I've added the tag: #" + tag + " to this task:\n" + task.toString();
    }
}
