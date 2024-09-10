package tasks;

import java.util.Objects;

/**
 * Event task class.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an event object.
     *
     * @param description Is the description of the event.
     * @param from Is the start date.
     * @param to Is the final date.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event object.
     *
     * @param description Is the description of the event.
     * @param from Is the start date.
     * @param to Is the final date.
     * @param completed Is a flag to indicate if completed.
     */
    public Event(String description, String from, String to, boolean completed) {
        super(description, completed);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getSaveFormat() {
        return String.format(
                "E | %d | %s | %s to %s",
                super.isCompleteAsInteger(),
                super.getDescription(),
                from,
                to
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                super.getCompletedStringRepresentation(),
                super.getDescription(),
                from,
                to
        );
    }

    /**
     * Loads an event object from storage.
     *
     * @param input Is the event object as string in storage.
     * @return An event object.
     */
    public static Event load(String input) {
        assert !input.isEmpty();
        String[] parameters = input.split("\\|");
        assert parameters.length == 4;
        String[] dateRange = parameters[3].split("to");
        boolean completed = parameters[1].trim().equals("1");
        return new Event(
                parameters[2].trim(),
                dateRange[0].trim(),
                dateRange[1].trim(),
                completed
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }
}
