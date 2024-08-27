package Command;

/* My import */
import Blitz.Storage;
import Blitz.TaskList;
import Blitz.Ui;
import Exception.BlitzEmptyTaskListException;
import Exception.BlitzException;
import Exception.BlitzIndexOutOfBoundsException;
import Exception.BlitzNumberFormatException;
import Task.Task;

public class CommandUnmark extends Command {
    private String param;

    public CommandUnmark(String command, String param) {
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

            task.unmarkDone();
            String[] toPrint = {"Ok, I've marked this task as not done yet:", "  [" + task.getType() + "]" + "[ ] " + task};

            storage.writeAllToFile(list);
            ui.printInDivider(toPrint);
        } catch (IndexOutOfBoundsException e) {
            throw new BlitzIndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new BlitzNumberFormatException();
        }
    }
}
