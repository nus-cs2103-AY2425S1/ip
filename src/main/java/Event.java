import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.from.format(INPUT_FORMATTER) +  " | "
                + this.to.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        String timeframe = String.format("(from: %s to: %s)", this.from.format(OUTPUT_FORMATTER),
                this.to.format(OUTPUT_FORMATTER));
        return String.format("[E]%s %s", super.toString(), timeframe);
    }
}
