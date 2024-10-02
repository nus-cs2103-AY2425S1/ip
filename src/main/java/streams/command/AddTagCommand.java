package streams.command;

import streams.exception.StreamsException;
import streams.task.Task;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to add a tag to a specific task.
 */
public class AddTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs an AddTagCommand with the given arguments.
     *
     * @param args The command arguments containing the task index and tag.
     * @throws StreamsException If the command format is invalid or the task index is not a number.
     */
    public AddTagCommand(String args) throws StreamsException {
        String[] parts = args.split(" ", 2);
        if (parts.length != 2) {
            throw new StreamsException("Invalid command format. Use: tag INDEX TAG");
        }
        try {
            this.taskIndex = Integer.parseInt(parts[0]) - 1;
            this.tag = parts[1].trim();
        } catch (NumberFormatException e) {
            throw new StreamsException("Invalid task index");
        }
    }

    /**
     * Executes the add tag command.
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
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new StreamsException("Invalid task index");
        }
        Task task = tasks.getTask(taskIndex);
        task.addTag(tag);
        ui.showMessage("Added tag '" + tag + "' to task: " + task);
        storage.save(tasks.getTasks());
    }
}
