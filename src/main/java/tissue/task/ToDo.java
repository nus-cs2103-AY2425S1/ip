package tissue.task;

/** Wrapper class for a ToDo event. */
public class ToDo extends Task {

    /**
     * Constructor for accepting isDone of type boolean.
     *
     * @param isDone True if task is done.
     * @param task The task to be stored.
     */
    public ToDo(boolean isDone, String task) {
        super(isDone, task);
    }

    /**
     * Constructor for accepting isDone of type int.
     *
     * @param isDone 1 if task is done.
     * @param task The task to be stored.
     */
    public ToDo(int isDone, String task) {
        super(isDone == 1, task);
    }

    @Override
    public String toString() {
        return super.getIsDone() ? "[T][X] " + super.getTask() : "[T][ ] " + super.getTask();
    }
}
