package tissue.task;

/**
 * Wrapper class for a ToDo event.
 */
public class ToDo extends Task {

    public ToDo(boolean isDone, String task) {
        super(isDone, task);
    }

    public ToDo(int isDone, String task) {
        super(isDone == 1, task);
    }

    @Override
    public String toString() {
        return super.getIsDone() ? "[T][X] " + super.getTask() : "[T][ ] " + super.getTask();
    }
}
