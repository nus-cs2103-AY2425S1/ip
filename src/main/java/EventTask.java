import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = null;
        this.to = null;

        // Validate the input date/time and then assign them
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(from.trim(), formatter);

        } catch (DateTimeException e) {
            throw new NetherException("The date/time format for the event FROM timing is invalid. Please use " +
                    "the format: yyyy-MM-dd HHmm.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.to = LocalDateTime.parse(to.trim(), formatter);

        } catch (DateTimeException e) {
            throw new NetherException("The date/time format for the event TO timing is invalid. Please use " +
                    "the format: yyyy-MM-dd HHmm.");
        }
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E|" + this.getStatusIcon() + "|" + this.getDescription() + "|" + this.from.format(formatter)
                + "|" + this.to.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + "(from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }
}
