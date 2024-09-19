package barcus.command;

import java.util.ArrayList;
import java.util.List;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to mark tasks at specified positions as completed.
 */
public class MarkCommand extends Command {
    private int[] pos;

    /**
     * Constructs a MarkCommand to mark tasks at the specified positions as done.
     *
     * @param pos an array of task positions to mark as completed, using 1-based indexing
     */
    public MarkCommand(int[] pos) {
        this.pos = pos;
        this.output = "";
    }

    /**
     * Executes the command by marking tasks at the specified positions in the task list as completed.
     *
     * @param tasks the task list containing the tasks to be marked as done
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

        output += "Good job! Have marked as done:\n";
        for (int i: queue) {
            tasks.markTask(i);
            output += tasks.getTaskString(i) + "\n";
        }

    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as marking tasks does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
