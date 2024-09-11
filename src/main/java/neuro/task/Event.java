package neuro.task;

/**
 * The {@code Event} class represents an event task, which is a type of task with a description,
 * beginning and ending time/date.
 * It extends the {@link Task} class, inheriting its properties and methods.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event object with the specified from and to time/date.
     * @param description The description of the task.
     * @param from The "from" or starting time/date for the event.
     * @param to The "to" or ending time/date for the event.
     */
    public Event(String description, String from, String to) {
        super(description);

        assert !from.isEmpty() : "From date should not be empty";
        assert !to.isEmpty() : "To date should not be empty";

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveData() {
        return "E | " + super.toSaveData() + " | " + this.from + " | " + this.to;
    }
}
