public class Deadline extends Task {
    private String due;
    public Deadline(String task, String due) {
        super(task);
        this.due = due;
    }
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.due);
    }

    public String toStorageString() {
        return String.format("D | %s | %s", this.taskString, this.due);
    }
}
