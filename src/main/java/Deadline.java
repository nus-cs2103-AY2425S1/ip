public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String addTaskToString() {
        return super.addTaskToString() + "\n" + "  [D] [ ] " + description + " (by: " + by + ")";
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[D] [X] " + description + " (by: " + by + ")";
        }
        return "[D] [ ] " + description + " (by: " + by + ")";
    }


}
