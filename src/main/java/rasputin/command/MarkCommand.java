package rasputin.command;

import rasputin.gui.Ui;
import rasputin.task.RasputinException;
import rasputin.task.TaskList;
import rasputin.task.InvalidTaskException;


/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command implements Undoable {

    private TaskList tasks;
    private int index;

    /**
     * Constructor for class MarkCommand
     *
     * @param tasks TaskList in which the task is to be marked
     * @param index Index of task to be marked
     */
    public MarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Marks the task at the given index of the TaskList as done.
     *
     * @return Rasputin's response as a String in case of successful or failed marking.
     * @throws InvalidTaskException If the given index is out of bounds of the TaskList.
     */
    @Override
    public String execute() throws InvalidTaskException {
        try {
            tasks.mark(index);
            String output = "Good job! Marked that as done for you.\n" + tasks.get(index).toString();
            tasks.setLastCommand(this);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
    }

    /**
     * Undoes the command ie. unmarks the specified task.
     *
     * @return Rasputin's response as a String
     * @throws RasputinException
     */
    @Override
    public String undo() throws RasputinException {
        tasks.unmark(index);
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
