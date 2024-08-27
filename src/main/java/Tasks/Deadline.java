package Tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime localDateTime;
    private final LocalDate localDate;

    public Deadline(String description, String deadline) throws DateTimeException {
        super(description);

        localDateTime = DateHandler.parseLocalDateTime(deadline);
        localDate = DateHandler.parseLocalDate(deadline);

        if (localDateTime == null && localDate == null) {
            throw new DateTimeException("");
        }
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        String date;

        if (localDateTime != null) {
            date = localDateTime.format(DateHandler.dateTimeFormat);
        } else {
            date = localDate.format(DateHandler.dateFormat);
        }

        return super.description + " (by: " + date + ") ";
    }
}
