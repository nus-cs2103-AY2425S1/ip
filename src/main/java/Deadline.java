public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) throws SageException {
        super(description);
        this.by = by;
        if (description.isEmpty() || by.isEmpty()) {
            throw new SageException("You need to add a task and a deadline!! -_-");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
