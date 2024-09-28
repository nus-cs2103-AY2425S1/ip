package oyster.tasks;

/**
 * Interface DatedTask for DeadlineTask and EventTask.
 */
public interface DatedTask {
    /**
     * Compares time now with end time of Task.
     *
     * @return Boolean whether the time is over the end of the Task.
     */
    boolean isDue();
}
