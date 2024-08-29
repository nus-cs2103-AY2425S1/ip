public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String addTaskToString() {
        return super.addTaskToString() + "\n"
                + " ".repeat(5) + "[D] [ ] " + description + " (by: " + by + ")" + "\n"
                + "\n"
                + "You can use the command \"list\" to view your list of tasks :D";
    }

    @Override
    public String taskToString() {
        if (isDone) {
            return "[D] [X] " + description + " (by: " + by + ")";
        }
        return "[D] [ ] " + description + " (by: " + by + ")";
    }

    @Override
    public String markDoneToString() {
        return super.markDoneToString() + "\n" + " ".repeat(5) + "[D] [X] " + description + " (by: " + by + ")";
    }

    @Override
    public String unmarkDoneToString() {
        return super.unmarkDoneToString() + "\n" + " ".repeat(5) + "[D] [ ] " + description + " (by: " + by + ")";
    }

    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n" + " ".repeat(5) + "[D] [X] " + description + " (by: " + by + ")";
        }
        return super.deleteTask() + "\n" + " ".repeat(5) + "[D] [ ] " + description + " (by: " + by + ")";
    }

}
