package lunabot.command;

import lunabot.exception.LunaBotException;
import lunabot.storage.Storage;
import lunabot.task.Task;
import lunabot.task.TaskList;
import lunabot.ui.Ui;

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
        // Guard clause
        if (input.length() < 8) {
            throw new LunaBotException("Invalid command format. No task number provided.");
        }
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        // Checks if task number provided is valid and within range
        if (index < 0 || index >= taskList.size()) {
            throw new LunaBotException("Invalid task number provided");
        }
        Task task = taskList.get(index);
        task.unmarkAsDone();
        storage.save(taskList.getTasks());
        return ui.printTaskUnmarked(task);
    }
}
