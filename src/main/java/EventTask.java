import java.time.LocalDate;
import java.time.LocalTime;

public class EventTask extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public EventTask(String description, boolean isDone, String date, String startTime, String endTime)
            throws InvalidDateException, InvalidTimeException {
        super(description, isDone);
        this.date = Parser.parseDate(date);
        this.startTime = Parser.parseTime(startTime);
        this.endTime = Parser.parseTime(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateToString(this.date) +
                " " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String simpleFormat() {
        return "E | " + super.simpleFormat() + " | " + Parser.dateToString(this.date)
                + " | " + this.startTime + " | " + this.endTime;
    }
}
