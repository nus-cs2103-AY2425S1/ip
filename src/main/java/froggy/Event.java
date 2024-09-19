package froggy;

/**
 * Represents a task with description and a beginning time and ending time.
 */
public class Event extends Task {

    protected final String from;
    protected final String to;

    /**
     * Constructor that takes in a description and two more descriptions.
     * Additional descriptions indicate starting and ending time.
     *
     * @param description String input.
     * @param from String input indicating start time.
     * @param to String input indicating end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toTxt() {
        return "E " + super.toTxt() + " | " + from + " | " + to;
    }

    @Override
    public boolean equals(Task task) {
        if (this == task) {
            return true;
        }
        if (!(task instanceof Event)) {
            return false;
        }
        Event event = (Event) task;
        return description.equals(event.getDescription()) && from.equals(event.from) && to.equals(event.to);
    }
}
