public class ToDo extends Task {
    public ToDo(String description) throws SageException {
        super(description);
        if (description.isEmpty()) {
            throw new SageException("Oh No! I don't know what task to do :(");
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
