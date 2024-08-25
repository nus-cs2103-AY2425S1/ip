package Cookie;
public class ToDo extends Task{

    /**
     * Constructor for ToDo class.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Another constructor for ToDo class.
     *
     * @param isDone Whether the task is done.
     * @param description Description of task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
