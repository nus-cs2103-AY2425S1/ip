package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzEmptyTaskListException;
import exception.BlitzException;
import exception.BlitzIndexOutOfBoundsException;
import exception.BlitzNumberFormatException;

import task.Task;

public class CommandMark extends Command {
    private String param;

    /**
     * Constructs a new CommandMark object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param param String containing the parameter for this command.
     */
    public CommandMark(String command, String param) {
        super(command);
        this.param = param;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to get the Task to be marked.
     * @param ui Ui to print the required text.
     * @param storage Storage to write to the file to update the marked Task.
     * @throws BlitzException If I/O error occurs, TaskList is empty or parameters are invalid.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        try {
            int ind = Integer.parseInt(this.param) - 1;

            if (list.isEmpty()) {
                throw new BlitzEmptyTaskListException();
            }

            Task task = list.getTask(ind);

            task.setDone(true);
            String[] toPrint = {"Nice! I've marked this task as done:",
                    "  [" + task.getType() + "]" + "[X] " + task};

            storage.writeAllToFile(list);
            ui.printInDivider(toPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
