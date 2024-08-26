import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDate by;
    
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    
    public String storageFormat() {
        return "D " + super.storageFormat() + " | " + by + "\n";
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatPrintVersion(by) + ")" + "\n";
    }
}
