import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class deadline extends Task{
    protected LocalDateTime dueDate;
    public deadline(String description, String dueDate){
        super("D", description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        String message = "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + dueDate.format(formatter) + ")";
        return message;
    }
}
