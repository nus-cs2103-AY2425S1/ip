package streams.command;

import streams.exception.StreamsException;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to remove a tag from a specific task.
 */
public class RemoveTagCommand extends Command {
    private int taskIndex;
    private String tag;

    /**
     * Constructs a RemoveTagCommand with the given arguments.
     *
     * @param args The command arguments containing the task index and tag.
     * @throws StreamsException If the command format is invalid or the task index is not a number.
     */
    public RemoveTagCommand(String args) throws StreamsException {
        String[] parts = args.trim().split("\\s+", 2);
        if (parts.length != 2) {
            throw new StreamsException("Invalid command format. Use: tag-remove [task number] [description]");
        }
        try {
            this.taskIndex = Integer.parseInt(parts[0]) - 1;
            this.tag = parts[1].trim();
        } catch (NumberFormatException e) {
            throw new StreamsException("Invalid task number. Please provide a valid number.");
        }
        assert taskIndex >= 0 : "Task index cannot be negative";
        assert !tag.isEmpty() : "Tag cannot be empty";
    }

    /**
     * Executes the remove tag command.
     *
     * @param tasks The task list to modify.
     * @param ui The user interface to display messages.
     * @param storage The storage to save updated task list.
     * @throws StreamsException If the task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (taskIndex >= tasks.size()) {
            throw new StreamsException("Invalid task index. The task does not exist.");
        }

        Task task = tasks.getTask(taskIndex);
        if (!task.hasTag(tag)) {
            throw new StreamsException("The task does not have the tag '" + tag + "'.");
        }

        task.removeTag(tag);
        storage.save(tasks.getTasks());
        ui.showMessage("Removed tag '" + tag + "' from task: " + task);
    }
}
