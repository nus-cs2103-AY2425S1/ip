package tasks;

import exceptions.EventUsageException;

public class Event extends Task {
    private final String from, to;

    public Event(String description, String from, String to) throws EventUsageException {
        super(description);

        if (description == null || description.equals("")
                || from == null || from.equals("")
                || to == null || to.equals("")) {
            throw new EventUsageException();
        }

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from, to);
    }
}
