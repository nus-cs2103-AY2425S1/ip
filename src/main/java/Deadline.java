import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline (String description, String by) throws DateTimeParseException{
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    public String getAdd(){
        return " /by " + this.by;
    }

    @Override
    public String getTypeLetter() {
        return "D";
    }
}
