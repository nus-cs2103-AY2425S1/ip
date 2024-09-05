package sumode.exception;

import sumode.task.Task;

/**
 * An Exception thrown for trying to mark a marked Task.
 */
public class AlreadyMarkedException extends SumoDException {

    /**
     * Constructor for AlreadyMarkedException
     * @param task The task which user tried to mark again.
     */
    public AlreadyMarkedException(Task task) {
        super("Sumo confused. This task is marked as done in the first place!\n"
                + "But SUMO will mark it as done again!\n" + task);
    }
}
