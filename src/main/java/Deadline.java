public class Deadline extends Task {

    protected String desc;
    protected String by;

    public Deadline(String description) {
        super(description);
        this.desc = description.split("deadline ")[1].split(" /by")[0];
        this.by = description.split("/by ")[1];
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + this.desc + " (by: " + by + ")";
    }
}
