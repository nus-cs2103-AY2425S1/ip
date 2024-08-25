public class DeadLine extends Task {
    private final String date;

    public DeadLine(String description, String time) {
        super(description);
        this.date = time;
    }

    @Override
    String getSaveFormat() {
        return String.format("D | %d | %s | %s", super.intComplete(), super.getDescription(), date);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.completedStringRepresentation(), super.getDescription(), date);
    }
}
