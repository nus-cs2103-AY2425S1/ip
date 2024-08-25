public class Deadline extends Task{
    protected String doneBy;
    public Deadline(String description, String doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.doneBy);
    }
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, doneBy);
    }
}
