import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate fromDate;
    private LocalTime fromTime;
    private LocalDate toDate;
    private LocalTime toTime;

    public Event(String description, String from, String to) throws JudeException {
        super(description);
        setDatesAndTimes(from, to);
    }

    public Event(String description, String from, String to, boolean isDone) throws JudeException {
        super(description, isDone);
        setDatesAndTimes(from, to);
    }

    public void setDatesAndTimes(String from, String to) throws JudeException {
        String[] fromDateAndTime = from.split(" ");
        String[] toDateAndTime = to.split(" ");

        try {
            String fromDate = fromDateAndTime[0].replace("/", "-");
            this.fromDate = LocalDate.parse(fromDate);

            String fromTime;
            if (fromDateAndTime[1].contains(":")) {
                fromTime = new StringBuilder(fromDateAndTime[1]).insert(2, ":").toString();
            } else {
                fromTime = fromDateAndTime[1];
            }
            this.fromTime = LocalTime.parse(fromTime);

            String toDate = toDateAndTime[0].replace("/", "-");
            this.toDate = LocalDate.parse(toDate);

            String toTime;
            if (fromDateAndTime[1].contains(":")) {
                toTime = new StringBuilder(toDateAndTime[1]).insert(2, ":").toString();
            } else {
                toTime = fromDateAndTime[1];
            }
            this.toTime = LocalTime.parse(toTime);

        } catch (DateTimeParseException de) {
            throw new JudeException("wrong date or time format was provided.");
        }
    }

    @Override
    public String toStringDetails() {
        return String.format("[E]%s (from: %s %s to: %s %s)",
                super.toStringDetails(),
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.fromTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.toTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s | %s | %s | %s",
                "E", getStatusBinary(), getDescription(), this.fromDate, this.fromTime, this.toDate, this.toTime);
    }
}
