public class Deadline extends Task {
    private String by;

    public Deadline(String s) {
        super(s.split(" /by ")[0]);
        this.by = s.split(" /by ")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by + ")";
    }
}