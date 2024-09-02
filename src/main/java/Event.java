import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public LocalDate fromDate;
    public LocalTime fromTime;
    public LocalTime toTime;

    public Event(String description) throws InvalidDescriptionException, InvalidDateTimeFormatException {
        // Split the description into the task and the time range
        super(description.split("/from")[0].trim());
        String[] arguments = description.split("/from ");

        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            throw new InvalidDescriptionException("Oh No! Please provide an event in the format: <task> /from <start> /to <end>");
        }

        String[] timeParts = arguments[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new InvalidDescriptionException("Oh No! Please provide a valid time range in the format: from <start date> <start time> /to <end time>");
        }

        // Parse 'from' and 'to' date and time
        String[] fromParts = timeParts[0].trim().split(" ");
        if (fromParts.length < 2) {
            throw new InvalidDescriptionException("Oh No! Please provide a valid time range in the format: from <start date> <start time> /to <end time>");
        }
        this.fromDate = parseDate(fromParts[0]);
        this.fromTime = parseTime(fromParts[1]);
        this.toTime = parseTime(timeParts[1].trim());
    }

    private LocalDate parseDate(String date) throws InvalidDateTimeFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid date format. Please use yyyy-MM-dd");
        } catch (Exception e) {
            throw e;
        }
    }

    private LocalTime parseTime(String time) throws InvalidDateTimeFormatException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid time format. Please use HHmm (e.g., 1800 for 6:00 PM)");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String write() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String fromDateString = fromDate != null ? fromDate.format(dateFormatter) : "unknown date";
        String fromTimeString = fromTime != null ? fromTime.format(timeFormatter) : "no specific time";
        String toTimeString = toTime != null ? toTime.format(timeFormatter) : "no specific time";
        return String.format("E | %d | %s | %s %s - %s\n", this.isDone ? 1 : 0, this.description, fromDateString, fromTimeString, toTimeString);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String fromDateString = fromDate != null ? fromDate.format(dateFormatter) : "unknown date";
        String fromTimeString = fromTime != null ? fromTime.format(timeFormatter) : "";
        String toTimeString = toTime != null ? toTime.format(timeFormatter) : "";
        return "[E]" + super.toString() + " (from: " + fromDateString + " " + fromTimeString + " to: " + toTimeString + ")";
    }
}
