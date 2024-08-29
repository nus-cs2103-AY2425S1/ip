import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.D);
        this.by = by;
    }

    public String changeDateFormat(String by) {
        if (by == null || by.trim().isEmpty()) {
            System.out.println("The deadline cannot be empty!");
            return "Invalid date";
        }

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

        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + by);
            return "Invalid date";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + changeDateFormat(by) + ")";
    }
}
