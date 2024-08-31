package colby;

import java.util.Objects;

public class Deadline extends Task {
    private final String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        String formattedEnd = formattedEnd = changeDateTime(end);
        return "[D]" + super.toString() + " (by: " + formattedEnd + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deadline deadline = (Deadline) o;
        return Objects.equals(description, deadline.description) &&
                Objects.equals(end, deadline.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, end);
    }
}
