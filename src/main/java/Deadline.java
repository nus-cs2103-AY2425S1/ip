import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline (String description, String deadline) throws JudeException {
        super(description);
        setDateAndTime(deadline);
    }

    public Deadline (String description, String dateTime, boolean isDone) throws JudeException {
        super(description, isDone);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    public void setDateAndTime(String deadline) throws JudeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            this.dateTime = LocalDateTime.parse(deadline, format);

        } catch (DateTimeParseException de) {
            throw new JudeException("wrong date or time format was provided.");
        }
    }



    @Override
    public String toStringDetails() {
        return String.format("[D]%s (by: %s)",
                super.toStringDetails(), this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s",
                "D", getStatusBinary(), getDescription(), this.dateTime);
    }
}
