package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;
import task.Deadline;
import task.Task;

/**
 * Represents a "deadline" command in the Blitz application.
 */
public class CommandDeadline extends Command {
    private String[] params;

    /**
     * Constructs a new CommandDeadline object with specified command String and parameters as Array of String.
     *
     * @param command Command String to be associated with this Command object.
     * @param params Array of String containing the parameters for this command.
     */
    public CommandDeadline(String command, String[] params) {
        super(command);
        this.params = params;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to add the new Task.
     * @param ui Ui to print the required text.
     * @param storage Storage to write to the file to add the new Task.
     * @return Execution result of the command as String.
     * @throws BlitzException If I/O error occurs.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task temp = new Deadline(params[0], "D", Task.convertStringToLocalDateTime(params[1]), false);

        list.addTask(temp);
        storage.writeOneToFile(temp);
        return ui.printTaskAdded("D", list.getSize(), temp);
    }
}
