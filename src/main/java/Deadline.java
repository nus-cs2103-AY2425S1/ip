package main.java;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    protected String by;
    protected LocalDateTime dt;

    public Deadline(String by, String desc) {
        super('D', desc);
        this.by = by;
        this.dt = parseDateTime(by);
    }



    @Override
    public String getDesc() {
        if (dt != null) {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
            return super.getDesc() + " (by: " + dt.format(outputFormatter) + ")";
        }
        return super.getDesc() + " (by: " + by + ")";
    }
}
