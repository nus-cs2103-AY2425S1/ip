package kita;

/**
 * A "ToDo" task
 */
public class ToDo extends Task {

    /**
     * Initialises a "ToDo" task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String type() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
