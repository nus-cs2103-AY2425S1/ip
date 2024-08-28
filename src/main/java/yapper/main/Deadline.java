package yapper.main;

public class Deadline extends Task {
    private String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String getDesc() {
        return "| D | " + super.getDesc() + " | " + formattedDate(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate(this.by) + ")";
    }
}
