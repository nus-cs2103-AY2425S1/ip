import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public LocalDate dueDate;
    public LocalTime dueTime;

    public Deadline(String description) throws InvalidDescriptionException, InvalidDateTimeFormatException {
        super(description.split("/by ")[0].trim());
        String[] arguments = description.split("/by ");

        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            throw new InvalidDescriptionException("Oh No! Please provide a deadline task in the format: <task> /by <date>");
        }

        String[] due = arguments[1].trim().split(" ");
        this.dueDate = parseDate(due[0]);
        this.dueTime = due.length > 1 ? parseTime(due[1]) : null;
    }

    private LocalDate parseDate(String date) throws InvalidDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    private LocalTime parseTime(String time) throws InvalidDateTimeFormatException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid time format. Please use HHmm (e.g., 1800 for 6:00 PM)");
        }
    }

    @Override
    public String write() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedDate = dueDate != null ? dueDate.format(dateFormatter) : "unknown date";
        String formattedTime = dueTime != null ? dueTime.format(timeFormatter) : "no specific time";
        return String.format("D | %d | %s | %s %s\n", this.isDone ? 1 : 0, this.description, formattedDate, formattedTime);
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
