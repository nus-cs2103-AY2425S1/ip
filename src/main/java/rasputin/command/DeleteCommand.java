package rasputin.command;

import rasputin.task.TaskList;
import rasputin.task.InvalidTaskException;

import rasputin.gui.Ui;


/**
 * Represents the command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {

    private TaskList tasks;
    private int index;

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
            tasks.remove(index);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
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
