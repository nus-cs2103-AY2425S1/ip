package barcus.command;

import java.util.ArrayList;
import java.util.List;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to unmark tasks at specified positions.
 */
public class UnmarkCommand extends Command {
    private int[] pos;

    /**
     * Constructs an UnmarkCommand to unmark tasks at the specified positions.
     *
     * @param pos an array of task positions to unmark, using 1-based indexing
     */
    public UnmarkCommand(int[] pos) {
        this.pos = pos;
        this.output = "";
    }

    /**
     * Executes the command by unmarking tasks at the specified positions in the task list.
     *
     * @param tasks the task list containing the tasks to be unmarked
     * @param storage the storage object (not used in this command)
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

        output += "No prob, have marked as undone:\n";
        for (int i: queue) {
            tasks.unmarkTask(i);
            output += tasks.getTaskString(i) + "\n";
        }
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as unmarking tasks does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
