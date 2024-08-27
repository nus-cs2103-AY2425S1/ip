import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws StanInvalidDateTimeFormatException {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
        validateDateTimeOrder();
    }

    private LocalDateTime parseDateTime(String dateTime) throws StanInvalidDateTimeFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new StanInvalidDateTimeFormatException("The event time must be in the format yyyy-MM-dd HHmm.\n"
                    + "E.g. /from 2021-07-29 1000 /to 2021-07-30 2200");
        }
    }

    private void validateDateTimeOrder() throws StanInvalidDateTimeFormatException {
        if (!from.isBefore(to)) {
            throw new StanInvalidDateTimeFormatException("The 'from' date and time must be before the 'to' date and time.");
        }
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return String.format("E | %s | %s | %s | %s", (isDone ? "1" : "0"), this.description, this.from.format(formatter), this.to.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        if (from.toLocalDate().equals(to.toLocalDate())) {
            return String.format("[E]%s (from: %s %s to: %s)",
                    super.toString(),
                    from.format(dateFormatter),
                    from.format(timeFormatter),
                    to.format(timeFormatter));
        } else {
            return String.format("[E]%s (from: %s to: %s)",
                    super.toString(),
                    from.format(dateFormatter) + ", " + from.format(timeFormatter),
                    to.format(dateFormatter) + ", " + to.format(timeFormatter));
        }
    }
}
