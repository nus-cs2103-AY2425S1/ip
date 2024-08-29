import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    public DeadlineTask(String description, String by) throws SageException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new SageException("Please format your deadline date in yyyy-MM-dd HHmm");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    @Override
    public String toSave() {
        return String.format("D | %s | %s", super.toSave(), by.format(inputFormatter));
    }
}