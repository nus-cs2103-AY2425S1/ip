public class Deadlines extends Task {

    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcons(), super.description, this.deadline);
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", this.done ? 1 : 0, this.description, this.deadline);
    }
}
