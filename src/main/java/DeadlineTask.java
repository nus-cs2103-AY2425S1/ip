public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDetails();
    }

    @Override
    public String getDetails() {
        return " (by: " + by + ")";
    }
}
