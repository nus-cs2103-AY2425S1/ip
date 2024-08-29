public class Deadline extends Task {
    private final String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }


    @Override
    public String toString() {
        String formattedEnd = formattedEnd = changeDateTime(end);
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + " (by: " + formattedEnd + ")";
    }
}
