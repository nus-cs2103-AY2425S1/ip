public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, int done) {
        super(description);
        this.by = by;
        if (done == 1) {
            this.isDone = true;
        }
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), (this.isDone ? 1 : 0), this.description, this.by);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}