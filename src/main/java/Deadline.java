public class Deadline extends Task{
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return "by: " + this.by;
    }

    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(" + getDeadline() + ")";
    }
}
