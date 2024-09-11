package rasputin.command;

import rasputin.gui.Ui;
import rasputin.task.RasputinException;
import rasputin.task.Task;
import rasputin.task.TaskList;
import rasputin.task.InvalidTaskException;



/**
 * Represents the command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command implements Undoable {

    private TaskList tasks;
    private int index;
    private Task deletedTask;

    public DeleteCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Removes the task at the given index from the TaskList.
     *
     * @throws InvalidTaskException If the given index is out of bounds of TaskList.
     */
    @Override
    public String execute() throws InvalidTaskException {
        try {
            String output = "Done, removed that task for you.\n" + tasks.get(index).toString();
            deletedTask = tasks.remove(index);
            tasks.setLastCommand(this);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
    }

    @Override
    public String undo() throws RasputinException {
        tasks.add(deletedTask);
        return Ui.printUndoCommand();
    }

    /**
     * Always returns false.
     *
     * @return False.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
