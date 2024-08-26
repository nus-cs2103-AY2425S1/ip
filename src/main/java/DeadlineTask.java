import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDateTime by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = null;

        // Validate the input date/time and then assign it
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeException e) {
            throw new NetherException("The date/time format for the deadline is invalid. Please use " +
                    "the format: yyyy-MM-dd HHmm.");
        }
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D|" + getStatusIcon() + "|" + this.getDescription() + "|" + this.by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + "(by: " + this.by.format(formatter) + ")";
    }
}
