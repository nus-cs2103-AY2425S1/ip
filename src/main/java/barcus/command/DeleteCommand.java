package barcus.command;

import java.util.ArrayList;
import java.util.List;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Task;
import barcus.tasklist.TaskList;

/**
 * Command to delete tasks at specified positions.
 */
public class DeleteCommand extends Command {
    private int[] pos;

    /**
     * Constructs a DeleteCommand to remove tasks at the specified positions.
     *
     * @param pos an array of task positions to delete, 1-based indexing
     */
    public DeleteCommand(int[] pos) {
        this.pos = pos;
        this.output = "";
    }

    /**
     * Executes the command by deleting tasks at the specified positions from the task list.
     *
     * @param tasks the task list from which tasks are deleted
     * @param storage the storage object to save the modified task list
     * @throws BarcusException if a specified position is out of range
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        List<Integer> queue = new ArrayList<>();
        for (int p: pos) {
            if (p > 0 && p <= tasks.getLength()) {
                queue.add(p - 1);
            } else {
                throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
            }
        }

        assert !queue.isEmpty();

        output += "Removed task:\n";
        for (int i: queue) {
            Task temp = tasks.deleteTask(i);
            output += temp + "\n";
        }
        output += "There are " + tasks.getLength() + " task(s) in the list.";
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as deleting tasks does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
