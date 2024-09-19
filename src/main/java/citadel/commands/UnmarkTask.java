package citadel.commands;

import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidArgException;
import citadel.task.TaskList;
import citadel.ui.TextUI;

/**
 * Represents the command to unmark a task as not done in the Citadel application.
 * This class extends {@link Command} and provides functionality to unmark a task
 * in the task list as incomplete.
 */
public class UnmarkTask extends Command {

    /**
     * Constructs a new {@code UnmarkTask} command with the specified input and task list.
     *
     * @param input The user input associated with the unmark task command.
     * @param tasks The task list containing the task to be unmarked as not done.
     */
    public UnmarkTask(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the unmark task command.
     * <p>
     * This method parses the user input to determine which task to unmark as not done,
     * updates the task in the task list, and displays a confirmation message.
     * If the specified index is out of bounds, a {@link CitadelInvalidArgException} is thrown.
     * </p>
     *
     * @throws CitadelException If the specified index is invalid or an error occurs during task unmarking.
     */
    @Override
    public String run() throws CitadelException {
        try {
            String[] words = input.split(" ");
            // Find task based on input given
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).unMark();
            return TextUI.printUnmark(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
