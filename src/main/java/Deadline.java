public class Deadline extends Task{
    protected String dt;

    /**
     * Constructs a Deadline object with the specified description and deadline date-time.
     *
     * @param description The description of the task.
     * @param dt The deadline date-time in the specified format.
     */
    public Deadline(String description, String dt) {
        super(description, "D");
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (due: " + this.dt + ")";
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.dt;
    }

}
