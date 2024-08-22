public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super("D", description);
        this.by = by;
    }

    @Override
    public String stringUI() {
        return super.stringUI() + " (by: " + this.by + ")";
    }

    @Override
    public String stringStorage() {
        return super.stringStorage() + " | " + this.by;
    }

}
