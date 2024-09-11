package boombotroz;

/**
 * Deals with TODO typed task.
 */
public class ToDo extends Task {
    /**
     * Creates ToDo object.
     *
     * @param mark state of completion for task.
     * @param task task description.
     * @param priority priority level of task.
     */
    public ToDo(boolean mark, String task, int priority) {
        super(mark, task, priority);

    }

    /**
     * @inheritDoc
     */
    @Override
    public void hasDate(Ui ui) throws BoomException {}

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String s;
        s = String.format("[T]%s", super.toString());
        return s;

    }

}
