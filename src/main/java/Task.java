import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    
    public void markAsDone() {
        this.isDone = true;
    }
    
    public void markAsUndone() {
        this.isDone = false;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String storageFormat() {
        String doneStatus = String.valueOf(this.isDone ? 1 : 0);
        return "| " + doneStatus + " | " + this.getDescription();
    }
    
    public String dateFormatPrintVersion(LocalDate dateConversion) {
        return dateConversion.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
