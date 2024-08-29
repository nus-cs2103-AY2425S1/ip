public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toTextFormat() {
        return "T|" + (super.isTaskDone() ? "T" : "F") + "|" + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
