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

    public Event(String description, String fromDate, String fromTime, String toDate, String toTime, boolean isDone)
            throws JudeException {
        super(description, isDone);
        this.fromDate = LocalDate.parse(fromDate);
        this.fromTime = LocalTime.parse(fromTime);
        this.toDate = LocalDate.parse(toDate);
        this.toTime = LocalTime.parse(toTime);
    }

    public void setDatesAndTimes(String from, String to) throws JudeException {
        String[] fromDateAndTime = from.split(" ");
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
            this.fromDate = LocalDate.parse(fromDateAndTime[0], dateFormat);

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            this.fromTime = LocalTime.parse(fromDateAndTime[1], timeFormat);

        } catch (DateTimeParseException de) {
            throw new JudeException("wrong fromDate or fromTime format was provided.");
        }

        String[] toDateAndTime = to.split(" ");
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
            this.toDate = LocalDate.parse(toDateAndTime[0], dateFormat);

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            this.toTime = LocalTime.parse(toDateAndTime[1], timeFormat);

        } catch (DateTimeParseException de) {
            throw new JudeException("wrong toDate or toTime format was provided.");
        }
    }

    @Override
    public String toStringDetails() {
        return String.format("[E]%s (from: %s %s to: %s %s)",
                super.toStringDetails(),
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.fromTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.toTime.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s %s | %s %s",
                "E", getStatusBinary(), getDescription(), this.fromDate, this.fromTime, this.toDate, this.toTime);
    }
}
