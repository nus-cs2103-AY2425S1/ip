public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    public String getAdd(){
        return " /by " + this.by;
    }

    @Override
    public String getTypeLetter() {
        return "D";
    }
}
