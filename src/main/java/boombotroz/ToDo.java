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
     */
    public ToDo(boolean mark, String task) {
        super(mark, task);

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
