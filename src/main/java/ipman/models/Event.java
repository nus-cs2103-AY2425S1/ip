package ipman.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that spans a particular duration
 */
public class Event extends Task {
    public static final char TASK_TYPE = 'E';
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an <code>Event</code> that starts from a particular date and
     * ends on another date.
     *
     * @param name name of the event
     * @param from date which the event starts
     * @param to date which the event ends
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an <code>Event</code> from a previously serialized
     * <code>Event</code>.
     *
     * @param serializedEvent string from previously serialized event
     * @return parsed <code>Event</code> from the serialized string
     * @see Task#serialize()
     */
    public static Event deserialize(String serializedEvent) {
        String[] values = serializedEvent.split("\\|");
        Event event = new Event(
            values[2],
            LocalDate.parse(values[3]),
            LocalDate.parse(values[4])
        );
        if (values[1].equals("X")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s|%s|%s",
            this.getTaskType(),
            this.isDone ? 'X' : 'O',
            this.name,
            this.from,
            this.to
        );
    }

    @Override
    public String toString() {
        return String.format(
            "%s  (from: %s to: %s)",
            super.toString(),
            this.from.format(dateFormat),
            this.to.format(dateFormat)
        );
    }
}
