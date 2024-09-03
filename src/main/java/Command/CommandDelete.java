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
 * Represents a "delete" command in the Blitz application.
 */
public class CommandDelete extends Command {
    private String param;

    /**
     * Constructs a new CommandDelete object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param param String containing the parameter for this command.
     */
    public CommandDelete(String command, String param) {
        super(command);
        this.param = param;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to delete the Task.
     * @param ui Ui to print the required text.
     * @param storage Storage to write to the file after removing the Task.
     * @return Execution result of the command as String.
     * @throws BlitzException If I/O error occurs, TaskList is empty or parameters are invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        try {
            int ind = Integer.parseInt(param) - 1;

            if (list.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = list.deleteTask(ind);
            storage.writeAllToFile(list);

            return "Noted. I've removed this task:\n"
                    + "  [" + task.getType() + "]" + "[" + (task.isDone() ? "X" : " ") + "] " + task;
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
