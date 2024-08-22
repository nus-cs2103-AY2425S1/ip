/** This class represents an Event. It is child of the Task class.
 * @author Lee Ze Hao (A0276123J)
 */

public class Event extends Task{
    private String from;
    private String to;

    /** Constructor for an Event object. Sets name upon creation.
     * @param name Name of Event object.
     * @param from Starting time of event.
     * @param to End time of event.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /** Returns string representation of the Event.
     * @return String containing indication of Event class, and string representation of parent Task class.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
