public class Deadline extends Task {

    protected String desc;
    protected String by;

    public Deadline(String description) {
        super(description);
        if (description.startsWith("deadline ")) {
            this.desc = description.split("deadline ")[1].split(" /by")[0];
            this.by = description.split("/by ")[1];
        } else if (description.startsWith("[D][ ] ")) {
            this.desc = description.split("[D] ")[1].split(" (")[0];
            this.by = description.split("(by: ")[1].split(")")[0];
            this.isDone = false;
        } else if (description.startsWith("[D][X] ")) {
            this.desc = description.split("[D][X] ")[1].split(" (")[0];
            this.by = description.split("(by: ")[1].split(")")[0];
            this.isDone = true;
        }
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + this.desc + " (by: " + by + ")";
    }
}
