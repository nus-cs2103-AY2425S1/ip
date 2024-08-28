package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.Task;
import barney.ui.Ui;

/**
 * Represents a command to unmark a task. Extends the Command class.
 *
 * @param argumentMap A HashMap containing the command arguments.
 */
public class UnmarkCommand extends Command {
    HashMap<String, String> argumentMap;

    /**
     * Represents an UnmarkCommand. This command is used to unmark an item.
     *
     * @param argumentMap A HashMap containing the arguments for the command.
     */
    public UnmarkCommand(HashMap<String, String> argumentMap) {
        super("unmark");
        this.argumentMap = argumentMap;
    }

    /**
     * Executes the UnmarkCommand, which unmarks a task in the task list.
     *
     * @param tasks The task list to operate on.
     * @param ui    The user interface to display messages.
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
        task.unmark();

        ui.printMarked(task);

        return true;
    }
}