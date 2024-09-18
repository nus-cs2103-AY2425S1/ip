package skibidi.task;

import skibidi.utils.MilitaryTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String by;
    private MilitaryTime time;

    /**
     * Creates a deadline task.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     * @param time The time of the deadline task.
     */
    public Deadline(String description, String by, MilitaryTime time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by + " " + time.toString() + ")";
    }

    @Override
    public String writeToFile() {
        return "D | " + super.writeToFile() + " | " + by + " | " + time.toString();
    }
}
