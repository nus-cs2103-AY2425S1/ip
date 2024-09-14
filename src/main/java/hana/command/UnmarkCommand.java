package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command to unmark task as done.
 * When executed, this command will unmark task as done, print a message and save.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a new UnmarkCommand with input.
     *
     * @param input The input from user.
     */
    public UnmarkCommand(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        this.taskNumber = Integer.parseInt(parts[1].trim());
    }

    /**
     * Executes the command to unmark task as done.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object to handle reading/writing of tasks.
     * @throws HanaException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException {
        if (taskList.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        taskList.markTaskAsDone(taskNumber, false);
        ui.printMarked(taskList.getTasks().get(taskNumber - 1), false);
        storage.save();
    }
}
