package LunaBot.command;

import LunaBot.exception.LunaBotException;
import LunaBot.storage.Storage;
import LunaBot.task.Task;
import LunaBot.task.TaskList;
import LunaBot.ui.Ui;

/**
 * Command to unmark a task from the taskList as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an Unmark Command from user input.
     *
     * @param input Full user input is taken in for the command.
     * @throws LunaBotException Handles wrong input format
     */
    public UnmarkCommand(String input) throws LunaBotException {
        try {
            // extracts index as a string and converts to an int
            this.index = Integer.parseInt(input.substring(7).trim()) - 1;
        } catch (NumberFormatException e) {
            // checks if user inout an int
            throw new LunaBotException(" Invalid task number format");
        }
    }

    /**
     * @param taskList Current list of tasks for the UnmarkCommand to operate on.
     * @param ui User interface that handles user input interactions and display messages.
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If the task number provided is invalid (either out of range or not a number).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        // Checks if task number provided is valid and within range
        if (index < 0 || index >= taskList.size()) {
            throw new LunaBotException("Invalid task number provided");
        }
        Task task = taskList.get(index);
        task.unmarkAsDone();
        storage.save(taskList.getTasks());
        ui.printTaskUnmarked(task);
    }
}
