package hoodini;

/**
 * Handles events that the user inputs
 */
public class Event extends Input {

    private String from;
    private String to;

    /**
     * Stores the String into Event Class
     *
     * @param input String input by user
     */
    public Event(String input) {
        super(input.split(" ", 2)[1]
                .split("/", 2)[0]);

        this.from = input.split("/from", 2)[1]
                    .split("/to", 2)[0];
        this.to = input.split("/to", 2)[1];

    }

    /**
     * Handles input read
     * from text file stored in local device.
     * @param input Task which the user wants to store.
     * @param from Starting time of event.
     * @param to Ending time of event.
     */

    public Event(String input, String from, String to) {
        super(input);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of Event.
     * @return String which represent the Event
     */
    @Override
    public String toString() {
        String str = "[E] " + super.toString() + "(from:"
                + this.from + " to:" + this.to + ")";
        return str;
    }
}
