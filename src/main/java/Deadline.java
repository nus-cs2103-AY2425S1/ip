public class Deadline extends Task {
    private String end;
    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";
    }

    @Override
    public String toSave() {
        return "D" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.end;
    }
}
