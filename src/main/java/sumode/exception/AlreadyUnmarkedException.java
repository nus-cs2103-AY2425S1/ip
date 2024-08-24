package sumode.exception;

import sumode.task.Task;

/**
 * An Exception thrown for trying to unmark a unmarked Task.
 */
public class AlreadyUnmarkedException extends Exception {
    /**
     * Constructor for AlreadyMarked Exception
     * @param task The task which user tried to mark again.
     */
    public AlreadyUnmarkedException(Task task) {
        super("Sumo confused. This task is not completed in the first place!\n"
                + "But SUMO will mark it as NOT done again!\n" + task);
    }
}
