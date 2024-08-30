package echochat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDateTime dt;

    public Deadline(String by, String desc) {
        super('D', desc);
        this.by = by;
        this.dt = parseDateTime(by);
    }

    /**
     * Get description of Deadline.
     * @return Task description with date included.
     */
    @Override
    public String getDesc() {
        if (dt != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
            return super.getDesc() + " (by: " + dt.format(outputFormatter) + ")";
        }
        return super.getDesc() + " (by: " + by + ")";
    }

    /**
     * Get the "by" String of the deadline
     * @return String stating when deadline is
     */
    public String getBy() {
        return this.by;
    }
}
