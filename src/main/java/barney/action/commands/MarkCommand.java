package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.Task;
import barney.ui.Ui;

/**
 * Represents a command to mark a task as completed. This command takes in an
 * argument map containing the index of the task to be marked. The task will be
 * marked as completed and the updated task will be printed to the user
 * interface.
 *
 * @param argumentMap The argument map containing the index of the task to be
 *                    marked.
 * @throws InvalidArgumentException If the index is not a valid task number or
 *                                  is out of range.
 * @return True if the command is executed successfully, false otherwise.
 */
public class MarkCommand extends Command {
    HashMap<String, String> argumentMap;

    /**
     * Creates a new MarkCommand object.
     *
     * @param argumentMap a HashMap containing the arguments for the command
     */
    public MarkCommand(HashMap<String, String> argumentMap) {
        super("mark");
        this.argumentMap = argumentMap;
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
        verifyFlags(argumentMap);

        String indexStr = argumentMap.get("index");

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