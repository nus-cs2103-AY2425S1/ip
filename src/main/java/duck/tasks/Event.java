package duck.tasks;

import duck.exceptions.EventUsageException;

public class Event extends Task {
    private final DateAndTime from;
    private final DateAndTime to;

    public Event(String description, DateAndTime from, DateAndTime to) throws EventUsageException {
        super(description);

        if (description == null || description.equals("")
                || from == null
                || to == null) {
            throw new EventUsageException();
        }

        this.from = from;
        this.to = to;
    }

    @Override
    public String getSaveString() {
        return String.format("E,%s,%s,%s,%s", isDone, description, from.getOriginalString(), to.getOriginalString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from.toString(), to.toString());
    }
}
