public class ToDo extends Task {
    public ToDo(String description) throws JiaException {
        super(description);
        if (description.isEmpty()) {
            throw new JiaException("Oh No! I don't know what task to do :(");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
