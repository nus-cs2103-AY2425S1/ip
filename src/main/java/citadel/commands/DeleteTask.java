package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.exception.CitadelInvalidArgException;
import citadel.exception.CitadelException;
import citadel.ui.TextUI;

/**
 * Represents the command to delete a task from the task list in the Citadel application.
 * This class extends {@link Command} and provides the functionality to remove a task based on user input.
 */
public class DeleteTask extends Command {

    /**
     * Constructs a new {@code DeleteTask} command with the specified input and task list.
     *
     * @param input The user input associated with the delete command.
     * @param tasks The task list from which the task will be deleted.
     */
    public DeleteTask(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the delete task command.
     * <p>
     * This method parses the user input to determine which task to delete, removes the task from the list,
     * and then displays a confirmation message. If the specified index is out of bounds, a
     * {@link CitadelInvalidArgException} is thrown.
     * </p>
     *
     * @throws CitadelException If the specified index is invalid or an error occurs during deletion.
     */
    @Override
    public void run() throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            Task t = tasks.remove(index - 1);
            TextUI.printDelete(tasks, t);
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
