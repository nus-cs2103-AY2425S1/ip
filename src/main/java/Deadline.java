public class Deadline extends Task {
    String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, E | Completion Status (0 or 1) | Description | Deadline
     */
    @Override
    public String convertToFileString() {
        return "D | " + super.convertToFileString() + String.format(" | %s\n", deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}
