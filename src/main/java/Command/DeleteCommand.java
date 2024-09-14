package Command;

import Tools.Storage;
import Tools.TaskList;

/**
 * Represents a command to delete a task from the task list. This command
 * is a specific implementation of the Command class, focused on task deletion.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand with specified task list, storage, and command text.
     *
     * @param tasks    The TaskList from which a task will be deleted.
     * @param storage  The Storage utility to handle persistence of changes.
     * @param command  The command string that indicates the specific task to be deleted.
     */
    public DeleteCommand(TaskList tasks, Storage storage, String command) {
        super(tasks, storage, command);
    }

    /**
     * Executes the deletion of a task based on the index specified in the command string.
     * The index is parsed from the command string and adjusted for zero-based indexing.
     */
    public void execute() {
        try {
            // Subtracting 1 to convert from 1-based index to 0-based index as used in ArrayList
            int taskIndex = Integer.parseInt(command.substring(7)) - 1;
            tasks.deleteTask(taskIndex);
            // Optionally, could add feedback message or logging here if needed
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index provided for deletion.");
        }
    }
}
