public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public  String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "D" + " | " + status + " | " + this.description
                + " | " + this.by;
    }
    @Override
    public String getStatus() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + " " + super.toString() + " (by: " + by + ")";
    }
}
