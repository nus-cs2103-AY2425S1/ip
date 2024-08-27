public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription()
                + " (by: " + this.by + ")";
    }

    public String toFileFormat() {
        return "D | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.by;
    }

    public static Deadline parseTask(String taskData) {
        if (taskData.startsWith("D |")) {
            String[] parts = taskData.split(" \\| ");
            String description = parts[2].trim();
            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);
            if (parts[1].trim().equals("1")) {
                deadline.markDone();
            }
            return deadline;
        }
        throw new IllegalArgumentException("Invalid Deadline format");
    }
}
