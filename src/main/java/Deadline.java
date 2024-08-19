public class Deadline extends Task{
    protected String dueWhen;

    public Deadline(String description, String dueWhen) {
        super(description);
        this.dueWhen = dueWhen;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.dueWhen + ")";
    }
}
