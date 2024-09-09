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
 * Represents a "mark" command in the Blitz application.
 */
public class MarkCommand extends Command {
    private String parameter;

    /**
     * Constructs a new CommandMark object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param parameter String containing the parameter for this command.
     */
    public MarkCommand(String command, String parameter) {
        super(command);
        this.parameter = parameter;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to get the Task to be marked.
     * @param ui Ui to print the required text.
     * @param storage Storage to write to the file to update the marked Task.
     * @return Execution result of the command as String.
     * @throws BlitzException If I/O error occurs, TaskList is empty or parameters are invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        try {
            int index = Integer.parseInt(this.parameter) - 1;

            if (list.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = list.getTask(index);
            task.setDone(true);
            storage.writeAllToFile(list);

            return ui.getStringForTaskMarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
