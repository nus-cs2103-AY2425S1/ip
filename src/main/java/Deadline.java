import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public String statement;
    public LocalDate dueDate;
    public LocalTime dueTime;

    public Deadline(String description, String by) {
        super(description);
        String[] due = by.split(" ");
        this.dueDate = parseDate(due[0]);
        this.dueTime = due.length > 1 ? parseTime(due[1]) : null; // Handle optional time
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

    @Override
    public String write() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedDate = dueDate != null ? dueDate.format(dateFormatter) : "unknown date";
        String formattedTime = dueTime != null ? dueTime.format(timeFormatter) : "no specific time";
        statement = String.format("D | %d | %s | %s %s\n", this.isDone ? 1 : 0, this.description, formattedDate, formattedTime);
        return statement;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = dueDate != null ? dueDate.format(dateFormatter) : "unknown date";
        String formattedTime = dueTime != null ? dueTime.format(timeFormatter) : "";
        return "[D]" + super.toString() + " (by: " + formattedDate + (dueTime != null ? " " + formattedTime : "") + ")";
    }
}
