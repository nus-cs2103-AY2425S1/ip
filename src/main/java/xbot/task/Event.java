package xbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class Event extends Task {
    protected String from, to;

    public Event(String description, String from, String to) {
        super(description, TaskType.E);
        this.from = from;
        this.to = to;
    }

    public String changeDateFormat(String by) {

        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
                } else {
                    LocalDate date = LocalDate.parse(by, formatter);
                    return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return "TimeDate cannot be converted to another format :'0";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + changeDateFormat(from) + " to: " + changeDateFormat(to) + ")";
    }
}
