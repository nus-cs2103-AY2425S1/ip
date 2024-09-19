package interfaces;

public class Deadlines extends Task {
    protected String by;

    /**
     * @param by due-date of deadline.
     * @inheritDoc Stores due-date of deadline.
     */
    public Deadlines(String description, String by) {
        super(description.replace("deadline ", ""));
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String loadString() {
        return "deadline " + this.description + " /by " + this.by + " | " + this.isDone + " | " + this.tag;
    }

}
