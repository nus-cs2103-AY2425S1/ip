import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    LocalDateTime dateTime;

    public DateTime(String dateTime) throws WrongDateTimeFormatException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.dateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }

    // for endTime
    public DateTime(String startTime, String endTime) throws WrongDateTimeFormatException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);

            if (endTime.contains("/")) {
                this.dateTime = LocalDateTime.parse(endTime, formatter);
            } else {
                String combinedDateTime = startDateTime.toLocalDate()
                        .format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " " + endTime;
                this.dateTime = LocalDateTime.parse(combinedDateTime, formatter);
            }
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException();
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String toStorageFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return dateTime.format(formatter);
    }
}
