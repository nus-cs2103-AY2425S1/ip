package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzEmptyTaskListException;
import exception.BlitzException;
import exception.BlitzIndexOutOfBoundsException;
import exception.BlitzNumberFormatException;
import task.Task;

/**
 * Represents a "unmark" command in the Blitz application.
 */
public class CommandUnmark extends Command {
    private String param;

    /**
     * Constructs a new CommandUnmark object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param param String containing the parameter for this command.
     */
    public CommandUnmark(String command, String param) {
        super(command);
        this.param = param;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to be used if required.
     * @param ui Ui to be used if required.
     * @param storage Storage to be used if required.
     * @return Execution result of the command as String.
     * @throws BlitzException If I/O error occurs, TaskList is empty or parameters are invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        try {
            int ind = Integer.parseInt(this.param) - 1;

            if (list.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = list.getTask(ind);
            task.setDone(false);

            String toPrint = "Ok, I've marked this task as not done yet\n:"
                    + "  [" + task.getType() + "]" + "[ ] " + task;

            storage.writeAllToFile(list);
            return toPrint;
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
