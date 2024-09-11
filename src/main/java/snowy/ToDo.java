package snowy;

/**
 * Represents a simple task to be done.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo with the specified name.
     * @param name the name of the ToDo.
     */
    public ToDo(String name) {
        super(name);
        assert !name.isEmpty() : "Deadline name should not be empty";
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[T]%s", temp);
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("T|%s", temp);
    }
}
