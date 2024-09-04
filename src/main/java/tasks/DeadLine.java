package tasks;

import java.util.Objects;

public class DeadLine extends Task {
    private final String byDate;

    public DeadLine(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    public DeadLine(String description, String byDate, boolean completed) {
        super(description, completed);
        this.byDate = byDate;
    }

    public String getByDate() {
        return byDate;
    }

    @Override
    public String getSaveFormat() {
        return String.format("D | %d | %s | %s", super.intComplete(), super.getDescription(), byDate);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.completedStringRepresentation(), super.getDescription(), byDate);
    }

    public static DeadLine load(String input) {
        String[] parameters = input.split("\\|");
        boolean completed = parameters[1].trim().equals("1");
        return new DeadLine(
                parameters[2].trim(),
                parameters[3].trim(),
                completed
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeadLine deadLine = (DeadLine) o;
        return Objects.equals(byDate, deadLine.byDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), byDate);
    }
}
