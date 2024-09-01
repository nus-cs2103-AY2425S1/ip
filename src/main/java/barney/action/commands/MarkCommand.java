package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.Task;
import barney.ui.Ui;

/**
 * Represents a command for marking a task as completed. Extends the
 * {@link Command} class.
 */
public class MarkCommand extends Command {

    /**
     * Creates a new MarkCommand object.
     *
     * @param argumentMap a HashMap containing the arguments for the command
     */
    public MarkCommand(HashMap<String, String> argumentMap) {
        super("mark", argumentMap);
    }

    /**
     * Executes the MarkCommand, marking a task as completed.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui    The Ui object for user interaction.
     * @return true if the command is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the task number is invalid or out of
     *                                  range.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        String indexStr = getParameter("index");

        if (!indexStr.matches("^\\d+$")) {
            throw new InvalidArgumentException("Please enter a task number!");
        }
        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidArgumentException("Task number out of range!");
        }

        Task task = tasks.get(index);
        task.mark();

        ui.printMarked(task);

        return true;
    }
}
