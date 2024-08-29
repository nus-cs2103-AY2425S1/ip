package neuro.task;

/**
 * The {@code Event} class represents an event task, which is a type of task with a description,
 * beginning and ending time/date.
 * It extends the {@link Task} class, inheriting its properties and methods.
 */
public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
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
