public class DeadLine extends Task {
    private final String time;

    public DeadLine(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    String getSaveFormat() {
        return String.format("D | %d | %s | %s",  super.intComplete(), super.getDescription(), time);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.completedStringRepresentation(), super.getDescription(), time);
    }
}
