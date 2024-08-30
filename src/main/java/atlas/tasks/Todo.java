package atlas.tasks;

public class Todo extends Task {
    /**
     * @param name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * @return
     */
    @Override
    public String toFileString() {
        return "T " + super.toFileString();
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
