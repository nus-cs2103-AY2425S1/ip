public class Deadline extends Task{
    String by;

    public Deadline(String d, String b) {
        super(d);
        this.by = b;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String additionalDescDetailsToFileFormat() {
        return " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by + ")";
    }
}
