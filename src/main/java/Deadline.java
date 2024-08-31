public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, boolean status, String deadline) {
        super(name, status);
        this.deadline = deadline;
    }

    @Override
    public String storeInFile() {
        return String.format("T | %s | %s | %s", super.storeInFile(), this.name, this.deadline);
    }

    @Override
    public String toString() {
        String desc = String.format("[D]%s (by: %s)", super.toString(), deadline);
        return desc;
    }
}
