package milutrock.tasks;

import milutrock.exceptions.InvalidTaskFormatException;

/**
 * A task with a from and to date.
 */
public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Parse string input to create an Event object with a name, start
     * date, and end date.
     * 
     * @param input String input from the user.
     * @return An Event object created from the given input. 
     */
    public static Event getEventFromInput(String input) throws InvalidTaskFormatException {
        if (input.length() < 6) {
            throw new InvalidTaskFormatException("Event");
        }

        String[] parts = input.substring(6).split(" /from ");
        if (parts.length != 2) {
            throw new InvalidTaskFormatException("Event");
        }

        String[] dates = parts[1].split(" /to ");
        if (dates.length != 2) {
            throw new InvalidTaskFormatException("Event");
        }

        return new Event(parts[0], dates[0], dates[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
