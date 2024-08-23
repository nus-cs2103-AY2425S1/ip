package citadel.commands;

import citadel.Task.TaskList;
import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidArgException;
import citadel.ui.TextUI;

/**
 * Represents the command to mark a task as done in the Citadel application.
 * This class extends {@link Command} and provides functionality to mark a task
 * in the task list as completed.
 */
public class MarkTask extends Command {

    /**
     * Constructs a new {@code MarkTask} command with the specified input and task list.
     *
     * @param input The user input associated with the mark task command.
     * @param tasks The task list containing the task to be marked as done.
     */
    public MarkTask(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the mark task command.
     * <p>
     * This method parses the user input to determine which task to mark as done,
     * marks the task in the task list, and displays a confirmation message.
     * If the specified index is out of bounds, a {@link CitadelInvalidArgException} is thrown.
     * </p>
     *
     * @throws CitadelException If the specified index is invalid or an error occurs during task marking.
     */
    @Override
    public void run() throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markAsDone();
            TextUI.printMark(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
