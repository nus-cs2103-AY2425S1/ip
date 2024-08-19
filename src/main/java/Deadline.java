public class Deadline extends Task {
    String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String str = String.format("[D]%s (by: %s)", super.toString(), this.deadline);
        return str;
    }
}
