package meowmeow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithin extends Task {
    protected LocalDate periodStart;
    protected LocalDate periodEnd;

    public DoWithin(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.periodStart = LocalDate.parse(from, formatter);
        this.periodEnd = LocalDate.parse(to, formatter);
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[W]" + super.toString() + " (between: " + periodStart.format(formatter) + " and: " + periodEnd.format(formatter) + ")";
    }

    @Override
    public String convertToFileFormat() {
        return "W | " + (isDone ? "1" : "0") + " | " + description + " | " + periodStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | " + periodEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
