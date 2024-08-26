import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    
    protected LocalDate start;
    protected LocalDate end;
    
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    
    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }
    
    public String storageFormat() {
        return "E " + super.storageFormat() + " | " + start + " | " + end + "\n";
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateFormatPrintVersion(start)  + " to: " + dateFormatPrintVersion(end) + ")" + "\n";
    }
}
