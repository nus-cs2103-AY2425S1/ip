public class Deadline extends Task {
    private String end;

    public Deadline(boolean status, String description, String end) {
        super(status, description);
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "";
    }

    @Override
    public String toString() {
        return "[D][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (by: " + this.end + ")";
    }

}
