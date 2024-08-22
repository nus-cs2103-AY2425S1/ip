public class Deadlines extends Task {
    String end;
    public Deadlines(String description, String end) {
        super(description, TaskType.DEADLINE);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + this.end + ")";
    }
}
