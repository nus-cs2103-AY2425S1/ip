package tasks;

public class ToDo extends Task {
    /**
     * Constructor for a ToDo
     * 
     * @param description The todo description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
