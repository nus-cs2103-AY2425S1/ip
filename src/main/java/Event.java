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

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getStartTime() + " to: " + this.getEndTime() + ")";
    }


}
