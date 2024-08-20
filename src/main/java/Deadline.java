public class Deadline extends Task {
    private String info;
    public Deadline(String description, String info) {
        super(description, info);
        this.info = info;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + info + ")";
    }
}
