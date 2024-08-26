package rotodo.tasklist;

/**
 * The Event class encapsulates the specific type of 
 * Task, called Events. Events are tasks that start 
 * at a specific date/time and ends at a specific 
 * date/time.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Event extends Task {
    String from;
    String to;

    public Event(String ...value) {
        super(value[0], value.length > 3 ? value[3] : "0");
        this.from = value[1];
        this.to = value[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String saveString() {
        return "E | " + saveString(from + " | " + to);
    }
}
