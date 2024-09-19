package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.tasklist.TaskList;

/**
 * OutOfRange class is used to check if the task number is out of range.
 */
public class OutOfRange {

    /**
     * Checks if the task number is out of range.
     *
     * @param index the task number to be checked.
     * @param tasks the task list.
     * @throws DarkpoolException if the task number is out of range.
     */
    public static void check(int index, TaskList tasks) throws DarkpoolException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DarkpoolException("do you know how to count? the task number is out of range");
        }
    }
}
