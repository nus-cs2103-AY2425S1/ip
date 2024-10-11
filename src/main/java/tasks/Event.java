package tasks;

import java.util.Objects;

/**
 * Event task class.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Returns an event object.
     *
     * @param description Description of the event.
     * @param from Start date.
     * @param to End date.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns an event object.
     *
     * @param description Description of the event.
     * @param from Start date.
     * @param to End date.
     * @param completed Completion state of Event object.
     */
    public Event(String description, String from, String to, boolean completed) {
        super(description, completed);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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
     * Returns an event object from storage.
     *
     * @param input String representation of Event object from save file.
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }
}
