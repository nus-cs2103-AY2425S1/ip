public class Deadline extends Task{
    private String deadline;

    public Deadline(String description) throws UnknownTimeException {
        super(description.substring(0, description.indexOf("/by") - 1));
        this.deadline = description.substring(description.indexOf("/by") + 4);
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    public String fileString() {
        return super.fileString() + " | " + this.deadline;
    }
}
