public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.symbol = "[D]";
    }

    @Override
    public String toString() { //prototype in case of future modification
        return super.toString() + " by: " + this.by;
    }
}
