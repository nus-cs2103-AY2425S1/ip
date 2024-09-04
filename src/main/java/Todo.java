public class Todo extends Task {
    public Todo (String description) throws EmptyDescriptionException {
        super(description);
    }

    public Todo (String description, boolean done) throws EmptyDescriptionException {
        super(description);
        if (done) {
            this.markTask();
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toUString() {
        String s = super.toUString();
        s += "001"; // Unique identifier for Todo Tasktype
        s += super.description;
        return s;
    }
}
