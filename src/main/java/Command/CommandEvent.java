package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzException;

import task.Event;
import task.Task;

public class CommandEvent extends Command {
    private String[] params;

    /**
     * Constructs a new CommandEvent object with specified command String and parameters as Array of String.
     *
     * @param command Command String to be associated with this Command object.
     * @param params Array of String containing the parameters for this command.
     */
    public CommandEvent(String command, String[] params) {
        super(command);
        this.params = params;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to add the new Task.
     * @param ui Ui to print the required text.
     * @param storage Storage to write to the file to add the new Task.
     * @throws BlitzException If I/O error occurs.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task temp = new Event(params[0], "E", Task.convertStringToLocalDateTime(params[1]),
                Task.convertStringToLocalDateTime(params[2]), false);

        list.addTask(temp);
        storage.writeOneToFile(temp);
        ui.printTaskAddedWithDivider("E", list.getSize(), temp);
    }
}
