public class Deadline extends Task {

    protected String desc;
    protected String by;

    public Deadline(String description) {
        super(description);
        if (description.startsWith("deadline ")) {
            this.desc = description.split("deadline ")[1].split(" /by")[0];
            this.by = description.split("/by ")[1];
        } else if (description.startsWith("[D][ ] ")) {
            String[] parts = description.split("\\[D\\]\\[ \\] ");
            this.desc = parts[1].split(" \\(by:")[0];
            String[] byParts = parts[1].split("\\(by: ");
            this.by = byParts[1].split("\\)")[0];
            this.isDone = false;
        } else if (description.startsWith("[D][X] ")) {
            String[] parts = description.split("\\[D\\]\\[X\\] ");
            this.desc = parts[1].split(" \\(by:")[0];
            String[] byParts = parts[1].split("\\(by: ");
            this.by = byParts[1].split("\\)")[0];
            this.isDone = true;
        }
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + this.desc + " (by: " + by + ")";
    }
}
