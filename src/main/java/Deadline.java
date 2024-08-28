public class Deadline extends Task {
    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getExtra() {
        return "/by " + by;
    }
    
    @Override
    public String getType() {
        return "deadline";
    }
}
