import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String desc;
    private String isDone;

    private static String[] timeFormats = {
            "HHmm",
            "HH:mm[:ss]"
    };

    private static String[] dateFormats = {
            "d/M/yyyy", // Date only
            "d/MM/yyyy",
            "dd/M/yyyy",
            "dd/MM/yyyy",
            "yyyy/M/d",
            "yyyy/MM/d",
            "yyyy/M/dd",
            "yyyy/MM/dd",
    };

    public Task(String desc) {
        this.desc = desc;
        this.isDone = "[ ]";
    }

    public void mark() {
        this.isDone = "[X]";
    }

    public void unmark() {
        this.isDone = "[ ]";
    }

    public String formattedDate(String time) {
        for (String timeFormat : timeFormats) {
            for (String dateFormat : dateFormats) {
                try {
                    StringBuilder fm = new StringBuilder(dateFormat).append(" ").append(timeFormat);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fm.toString());
                    LocalDateTime formattedDateTime = LocalDateTime.parse(time, formatter);
                    return formattedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }

        for (String fm : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fm);
                LocalDate formattedDate = LocalDate.parse(time, formatter);
                return formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return time;
    }

    public String getDesc() {
        return this.isDone.charAt(1) + " | " + this.desc;
    }

    @Override
    public String toString() {
        return this.isDone + " " + this.desc;
    }
}
