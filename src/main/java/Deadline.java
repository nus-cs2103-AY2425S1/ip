public class Deadline extends Task {
    private String deadline;
    public Deadline(String name, boolean status, String deadline) {
        super(name, status);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
