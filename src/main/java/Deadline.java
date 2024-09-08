public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + by;
    }

    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + by + ")";
    }
}
