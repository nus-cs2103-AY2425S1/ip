public class Deadline extends Task {

    private final String due;

    public Deadline(String name, String due) {
        super(name);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
