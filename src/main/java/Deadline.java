public class Deadline extends Task {
    String deadline;
    public Deadline(String name, String deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline) {
        this(name, deadline, false);
    }

    @Override
    public String toString() {
        String str = String.format("[D]%s (by: %s)", super.toString(), this.deadline);
        return str;
    }
}
