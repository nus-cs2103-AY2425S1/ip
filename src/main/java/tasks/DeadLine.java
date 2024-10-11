package tasks;

import java.util.Objects;

/**
 * Deadline task class.
 */
public class DeadLine extends Task {
    private final String byDate;

    /**
     * Returns a DeadLine object.
     *
     * @param description Description of the task.
     * @param byDate Deadline date.
     */
    public DeadLine(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a DeadLine object.
     *
     * @param description Description of the task.
     * @param byDate Deadline date.
     * @param completed Completion state of Deadline object.
     */
    public DeadLine(String description, String byDate, boolean completed) {
        super(description, completed);
        this.byDate = byDate;
    }

    public String getByDate() {
        return byDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveFormat() {
        return String.format(
                "D | %d | %s | %s",
                super.isCompleteAsInteger(),
                super.getDescription(),
                byDate
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                super.getCompletedStringRepresentation(),
                super.getDescription(),
                byDate
        );
    }

    /**
     * Returns deadline object from a persistent storage.
     *
     * @param input String representation of DeadLine object from save file.
     * @return An instance of DeadLine class.
     */
    public static DeadLine load(String input) {
        assert !input.isBlank();
        String[] parameters = input.split("\\|");
        assert parameters.length == 4;
        boolean completed = parameters[1].trim().equals("1");
        return new DeadLine(
                parameters[2].trim(),
                parameters[3].trim(),
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
        DeadLine deadLine = (DeadLine) o;
        return Objects.equals(byDate, deadLine.byDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), byDate);
    }
}
