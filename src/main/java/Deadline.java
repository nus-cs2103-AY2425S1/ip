public class Deadline extends Task {
    private String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + "(by: " + this.end + ")";
    }

}
