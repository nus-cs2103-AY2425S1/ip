/**
 * Event is a class that encapsulates the characteristics of an Event Task.
 * It extends from the class Task,
 * and contains additional characteristics of
 * startTime and endTime
 */
public class Event extends Task {

    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    /**
     * The method converts the task to its text representation in the file.
     */
    @Override
    public String toText() {
        String completed = isDone ? "1" : "0";
        return "E | " + completed + " | " + super.description + " | " + startTime + " | " + endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
    }


}
