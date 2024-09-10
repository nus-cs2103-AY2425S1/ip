package commands;

import exceptions.BrockException;
import storage.Storage;
import task.TaskList;
import utility.CommandUtility;

/**
 * Represents an unmark command entered by the user.
 */
public class UnmarkCommand extends Command {
    /**
     * Stores the command string associated with unmark command.
     *
     * @param command Command string.
     */
    public UnmarkCommand(String command) {
        super(command);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if unmark command is valid.
     * If so, it unmarks the associated task in {@code tasks} and updates the save file.
     * Displays a response indicating it has successfully unmarked the task.
     * </p>
     *
     * @throws BrockException If unmark command is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws BrockException {
        String command = super.getCommand();
        CommandUtility.validateStatus(command, CommandUtility.Action.UNMARK, tasks);

        int taskIndex = CommandUtility.getTaskIndex(command);
        boolean isSuccessful = tasks.unmarkTask(taskIndex);

        // Update the save file
        String tasksString = tasks.listTasks();
        storage.writeToFile("", false);
        storage.writeToFile(tasksString, true);

        if (!isSuccessful) {
            return "Task has not been marked yet!";
        }

        return "OK, I've marked this task as not done yet:\n"
                + "  "
                + tasks.getTaskDetails(taskIndex);
    }
}
