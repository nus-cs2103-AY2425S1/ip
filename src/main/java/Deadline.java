public class Deadline extends Task {

    String deadlineDate;
    public Deadline(String name, String deadlineDate) {
        super(name);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadlineDate);
    }
}
