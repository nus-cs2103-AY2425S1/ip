package skibidi.task;

import skibidi.utils.MilitaryTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    private MilitaryTime startTime;
    private MilitaryTime endTime;

    /**
     * Creates an event task.
     *
     * @param description The description of the event task.
     * @param from The starting location of the event task.
     * @param startTime The starting time of the event task.
     * @param to The ending location of the event task.
     * @param endTime The ending time of the event task.
     */
    public Event(String description, String from, MilitaryTime startTime, String to, MilitaryTime endTime) {
        super(description);
        this.from = from;
        this.to = to;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (from: " + from + " " + startTime.toString()
                + " to: " + to + " " + endTime.toString() + ")";
    }

    @Override
    public String writeToFile() {
        return "E | " + super.writeToFile() + " | " + from + " | " + startTime.toString() + " | " + to + " | " + endTime.toString();
    }
}
