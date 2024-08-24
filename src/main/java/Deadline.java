public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() { //prototype in case of future modification
        return "[D]" + super.toString() + " by: " + this.by;
    }
}
