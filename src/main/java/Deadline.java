public class Deadline extends Task {
    private String day;
    public Deadline(String description, String day) {
        super(description);
        this.day = day;
    }

    public String formatText() {
        return String.format("D | %s | %s | %s", super.getDoneString(), super.getDescription(), this.day);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.day);
    }
}
