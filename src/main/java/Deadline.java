public class Deadline extends Task {
    private String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getSaveFormat() {
        return "D | " + super.getSaveFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.by);
    }
}
