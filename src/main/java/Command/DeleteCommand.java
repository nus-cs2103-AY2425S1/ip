package Command;

import Task.TaskList;
import Task.InvalidTaskException;

import Ui.Ui;


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
    public void execute() throws InvalidTaskException {
        try {
            Ui.printText("Done, removed that task for you.\n" + tasks.get(index).toString());
            tasks.remove(index);
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
