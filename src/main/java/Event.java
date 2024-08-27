import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String taskDescription, String from, String to) throws MeejuException{
        super(taskDescription, false);
        String[] dateAndTimeFrom = from.split(" ");
        String[] dateAndTimeTo = to.split(" ");

        String exceptionMessage = "I'm having a bit of trouble understanding the task.\n"
                + "Could you please explain it using the correct format?\n"
                + "The Correct format is -> event <desc> /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM";

        if (dateAndTimeFrom.length != 2 || dateAndTimeTo.length != 2 ) {
            throw new MeejuException(exceptionMessage);
        }

        String dateRawFrom = dateAndTimeFrom[0];
        String timeRawFrom = dateAndTimeFrom[1];
        String dateRawTo = dateAndTimeTo[0];
        String timeRawTo = dateAndTimeTo[1];
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalDate fromDate;
        LocalTime fromTime;
        LocalDate toDate;
        LocalTime toTime;
        try {
            fromDate = LocalDate.parse(dateRawFrom, dateFormatter);
            fromTime = LocalTime.parse(timeRawFrom, timeFormatter);
            toDate = LocalDate.parse(dateRawTo, dateFormatter);
            toTime = LocalTime.parse(timeRawTo, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new MeejuException(exceptionMessage);
        }
        this.from = LocalDateTime.of(fromDate, fromTime);
        this.to = LocalDateTime.of(toDate, toTime);
    }

    public String serializeDetails() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedDateFrom = this.from.toLocalDate().format(dateFormatter);
        String formattedTimeFrom = this.from.toLocalTime().format(timeFormatter);
        String formattedDateTo = this.to.toLocalDate().format(dateFormatter);
        String formattedTimeTo = this.to.toLocalTime().format(timeFormatter);
        return "E !- " + this.getIsDone() + "!- "
                + this.getTaskDescription() + "!- "
                + formattedDateFrom + " " + formattedTimeFrom + "!- "
                + formattedDateTo + " " + formattedTimeTo + "\n";
    }
    private final String TASK_ICON = "[E]";

    public String getFrom() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDate = this.from.toLocalDate().format(dateFormatter);
        LocalTime time = this.from.toLocalTime();
        return formattedDate + " " + time + "HRS";
    }

    public String getTo() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDate = this.to.toLocalDate().format(dateFormatter);
        LocalTime time = this.to.toLocalTime();
        return formattedDate + " " + time + "HRS";
    }
    @Override
    public String toString() {
        return TASK_ICON + super.toString() +  " (from: " + this.getFrom()
                    + " to: " + this.getTo() + ")";
    }
}
