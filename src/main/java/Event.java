import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public String from;

    public String[] fromFull;
    public String to;

    public LocalDate fromDate;

    public LocalTime fromTime;
    public String statement;

    public Event(String description, String from, String to) {
        super(description);
        fromFull = from.split(" ");
        this.fromDate = parseDate(fromFull[0]);
        this.fromTime = fromFull.length > 1 ? parseTime(fromFull[1]) : null;
        this.to = to;
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd");
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error while parsing the date");
            return null;
        }
    }


    private LocalTime parseTime(String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HHmm (e.g., 1800 for 6:00 PM)");
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error while parsing the time");
            return null;
        }
    }

    public String write()
    {
        statement = String.format("E | %d | %s | %s-%s\n", this.isDone ? 1 : 0, this.description, this.from, this.to);
        return statement;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}