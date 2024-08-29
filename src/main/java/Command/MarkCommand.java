package Command;

import Task.TaskList;
import Task.InvalidTaskException;

import Ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private TaskList tasks;
    private int index;

    public MarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Marks the task at the given index of the TaskList as done.
     *
     * @throws InvalidTaskException If the given index is out of bounds of the TaskList.
     */
    @Override
    public void execute() throws InvalidTaskException {
        try {
            tasks.mark(index);
            Ui.printText("Marked that as done for you.\n" + tasks.get(index).toString());
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
