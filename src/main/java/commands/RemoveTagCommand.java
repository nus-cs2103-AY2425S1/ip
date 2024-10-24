package commands;

import java.io.IOException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Tag;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to remove a tag from a task.
 */
public class RemoveTagCommand extends Command {
    private final int taskIndex;
    private final Tag tag;

    /**
     * Creates a new RemoveTagCommand.
     *
     * @param input The input string containing the task number and tag.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public RemoveTagCommand(String input) throws SkibidiException {
        String[] parts = input.split(" ", 3);
        if (parts.length < 3) {
            throw new SkibidiException("Please provide a task number and a tag.");
        }
        try {
            this.taskIndex = Integer.parseInt(parts[1]) - 1; // Convert to 0-based index
            this.tag = new Tag(parts[2]);
        } catch (NumberFormatException e) {
            throw new SkibidiException("Please enter a valid task number.");
        }
    }

    /**
     * Executes the command to remove a tag from a task.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        try {
            Task task = storage.getTask(taskIndex);
            task.removeTag(tag);
            storage.saveTasks();
            return ui.outputMessage("Removed tag from task:\n  " + task);
        } catch (IOException e) {
            return ui.outputMessage(e.getMessage());
        }
    }
}
