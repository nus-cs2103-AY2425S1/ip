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

    public CommandMark(String command, String param) {
        super(command);
        this.param = param;
    }

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
