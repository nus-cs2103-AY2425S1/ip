import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline (String description, String deadline) throws JudeException {
        super(description);
        setDateAndTime(deadline);
    }

    public Deadline (String description, String date, String time, boolean isDone) throws JudeException {
        super(description, isDone);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public void setDateAndTime(String deadline) throws JudeException {
        String[] dateAndTime = deadline.split(" ");
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
            this.date = LocalDate.parse(dateAndTime[0], dateFormat);

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            this.time = LocalTime.parse(dateAndTime[1], timeFormat);

        } catch (DateTimeParseException de) {
            throw new JudeException("wrong date or time format was provided.");
        }
    }



    @Override
    public String toStringDetails() {
        return String.format("[D]%s (by: %s %s)",
                super.toStringDetails(), this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.time.format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s %s",
                "D", getStatusBinary(), getDescription(), this.date, this.time);
    }
}
