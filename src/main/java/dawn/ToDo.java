package dawn;
public class ToDo extends Task {
    /**
     * Creates a new instance of a ToDo task
     *
     * @param desc
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("  [T][%s] %s", super.getStatusIcon(), super.toString());
    }
}
