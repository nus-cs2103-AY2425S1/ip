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
    private String parameter;

    /**
     * Constructs a new CommandDelete object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param parameter String containing the parameter for this command.
     */
    public CommandDelete(String command, String parameter) {
        super(command);
        this.parameter = parameter;
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
            int index = Integer.parseInt(parameter) - 1;

            if (list.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task deletedTask = list.deleteTask(index);
            storage.writeAllToFile(list);

            return ui.getStringForTaskDeleted(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
