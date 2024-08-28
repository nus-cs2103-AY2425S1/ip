package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;
import task.Deadline;
import task.Task;

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
     * @throws BlitzException If I/O error occurs.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        Task temp = new Deadline(params[0], "D", Task.stringToLocaldatetime(params[1]), false);

        list.addTask(temp);
        storage.writeOneToFile(temp);
        ui.printTaskAddedWithDivider("D", list.size(), temp);
    }
}
