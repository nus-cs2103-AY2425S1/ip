import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.DateTime;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private LocalDate by;
    

    public Deadline(String description, String by) {
        super(description);
        this.by = DateTime.parseDate(by);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + DateTime.dmy.format(by) + ")";
    }
    
    @Override
    public String toStorage() {
        return TYPE + "|" + super.toStorage() + "|" + by;
    }
}
