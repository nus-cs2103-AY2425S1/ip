package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzEmptyTaskListException;
import exception.BlitzException;

import task.Task;

public class CommandList extends Command {
    /**
     * Constructs a new CommandList object with specified command String.
     *
     * @param command Command String to be associated with this Command object.
     */
    public CommandList(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to get all the existing Task.
     * @param ui Ui to print the required text.
     * @param storage Storage to be used if required.
     * @throws BlitzException If TaskList is empty.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        //readFromFile();
        if (list.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        String[] toPrint = new String[list.getSize() + 1];
        toPrint[0] = "Here are the tasks in your list:";

        for (int i = 0; i < list.getSize(); i++) {
            Task curr = list.getTask(i);
            toPrint[i + 1] = "    " + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.isDone() ? "X" : " ") + "] " + curr;
        }

        ui.printInDivider(toPrint);
    }
}
