package bottle.task;

/**
 * The type Todo.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param desc the desc
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | ";
    }
}
