package duck.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateAndTime {
    private LocalDate date;
    private String str;

    public DateAndTime(String str) throws DateTimeParseException {
        this.str = str;
        this.date = LocalDate.parse(str);
    }

    public String getOriginalString() {
        return str;
    }

    public String toString() {
        return String.format("%s %s %s", date.getDayOfMonth(), date.getMonth(), date.getYear());
    }
}
