public class Deadline extends Task {
    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "D | 1 | " + this.getTitle() + " | " + deadline;
        } else {
            return "D | 0 | " + this.getTitle() + " | " + deadline;
        }
    }
}
