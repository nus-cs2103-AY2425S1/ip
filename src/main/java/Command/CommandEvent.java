package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;
import task.Event;
import task.Task;

/**
 * Represents an "event" command in the Blitz application.
 */
public class CommandEvent extends Command {
    private String[] params;

    /**
     * Constructs a new CommandEvent object with specified command String and parameters as Array of String.
     *
     * @param values A variable number of String arguments associated with this Command object..
     */
    public CommandEvent(String... values) {
        super(values[0]);
        this.params = new String[values.length - 1];
        System.arraycopy(values, 1, params, 0, params.length);
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
        Task temp = new Event(params[0], "E", Task.convertStringToLocalDateTime(params[1]),
                Task.convertStringToLocalDateTime(params[2]), false);

        list.addTask(temp);
        storage.writeOneToFile(temp);
        return ui.printTaskAdded("E", list.getSize(), temp);
    }
}
