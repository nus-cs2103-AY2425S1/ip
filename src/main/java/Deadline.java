public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String desc = String.format("[D]%s (by: %s)", super.toString(), deadline);
        return desc;
    }
}
