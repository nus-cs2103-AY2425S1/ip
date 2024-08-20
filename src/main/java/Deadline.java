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

    public Deadline() {
        super();
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[D][X]: %s (%s)", getDescription(), this.deadline);
        } else {
            return String.format("[D][ ]: %s (%s)", getDescription(), this.deadline);
        }
    }
}
