package echochat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDateTime dateTime;

    public Deadline(String by, String desc) {
        super('D', desc);
        this.by = by;
        this.dateTime = parseDateTime(by);
    }

    /**
     * Get description of Deadline.
     *
     * @return Task description with date included.
     */
    @Override
    public String getDesc() {
        if (dateTime != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
            return super.getDesc() + " (by: " + dateTime.format(outputFormatter) + ")";
        }
        return super.getDesc() + " (by: " + by + ")";
    }

    /**
     * Get the "by" String of the deadline.
     *
     * @return String stating when deadline is
     */
    public String getBy() {
        return this.by;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Deadline.class) {
            return ((Deadline) o).getDesc().equals(this.getDesc())
                    && ((Deadline) o).getBy().equals(this.getBy());
        }
        return false;
    }
}
