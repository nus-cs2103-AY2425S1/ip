public class DeadlineTask extends Task {
    private final String by;

    public DeadlineTask(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String simpleFormat() {
        return "D | " + super.simpleFormat() + " | " + by;
    }
}
