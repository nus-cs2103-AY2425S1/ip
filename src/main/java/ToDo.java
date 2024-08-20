public class ToDo extends Task {
    protected String by;

    public ToDo(String description, String by) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
