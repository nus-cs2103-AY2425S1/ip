package yapper.components;


/**
 * Represents an event with a description and a time range.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event with the specified description, start date, and end date.
     *
     * @param description the description of the event
     * @param from the start date of the event in string format
     * @param to   the end date of the event in string format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted description of the event meant an input into the save file.
     * The format is: "| E | {description} | {formatted from date} ----- {formatted to date}".
     *
     * @return a string representing the formatted description of the event
     */
    @Override
    public String getDesc() {
        return "| E | " + super.getDesc() + " | " + super.formattedDate(this.from) + " ----- "
                + super.formattedDate(this.to);
    }

    /**
     * Returns a string representation of the event.
     * The format is: "[E]{description} (from: {formatted from date} to: {formatted to date})".
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.formattedDate(this.from) + " to: "
                + super.formattedDate(this.to) + ")";
    }
}
