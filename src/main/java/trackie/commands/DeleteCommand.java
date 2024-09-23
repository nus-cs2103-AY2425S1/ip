package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.tasks.Task;
import trackie.ui.TrackieException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a new DeleteCommand with the given arguments.
     *
     * @param arguments An array of Strings containing the command arguments.
     *                  Typically, this would include the index of the task to be deleted.
     */
    public DeleteCommand(String[] arguments) {
        super.arguments = arguments;
    }

    /**
     * Executes the delete command.
     * This method attempts to delete a task from the task list based on the provided arguments,
     * saves the updated task list. If the index provided is invalid, an exception will be thrown
     * and handled, causing an error message to be printed.
     *
     * @param tasklist The TaskList object from which a task will be deleted.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        try {
            if (arguments.length == 1) {
                throw new TrackieException("Please specify an index to unmark!");
            }

            int number = Integer.parseInt(arguments[1]);

            if (number < 1 || number > tasklist.size()) {
                throw new TrackieException("Invalid index.");
            }

            Task target = tasklist.getTasks().get(number - 1);
            tasklist.deleteTask(number - 1);
            storage.save();
            return "Deleted: " + target.toString();
        } catch (TrackieException e) {
            return e.getMessage();
        }
    }
}
