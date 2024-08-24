import exceptions.MissingParametersException;

public class Deadlines extends Task{
    private String by;

    /**
     * @param description The Description of the Deadline with /by to state the dateline
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Alternate constructor for loading isDone directly
     * @param isDone 1 or 0 indicating if task is done
     * @param description The Description of the Deadline
     * @param by by to state the dateline of the task
     */
    public Deadlines(String isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * A string that matches the format for writing it to file
     * @return A string to be written to a txt file
     */
    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by;
    }
}
