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

    @Override
    public String savedString() {
        return "D | " + super.savedString() + " | " + this.due;
    }
}
