package atreides.task;

/**
 * Represents an event task that includes a start and end date-time.
 * Inherits common behavior and properties from the Task class.
 */
public class Events extends Task {
    protected String startDT;
    protected String endDT;

    /**
     * Constructs an Events object with the specified description and start/end date-times.
     *
     * @param description The description of the event task.
     * @param startEnd An array containing the start and end date-times of the event.
     */
    public Events(String description, String[] startEnd) {
        super(description);
        this.startDT = startEnd[0];
        this.endDT = startEnd[1];
    }

    /**
     * Checks if two Events objects are equal based on their start and end date-times.
     *
     * @param obj The object to be compared with this Events object.
     * @return true if obj is the same as this object or if it is an Events instance with the same start and end.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj instanceof Events other) {
            return other.startDT.equals(this.startDT) && other.endDT.equals(this.endDT);
        }
        return false;
    }

    @Override
    public String write() {
        return "E" + super.write() + " | " + startDT + "-" + endDT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDT + " to: " + this.endDT + ")";
    }
}
