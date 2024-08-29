import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Task {
    protected LocalDateTime by;

    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")" ;
    }

    @Override
    public String toStore(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "D/"+ super.getStatus()+ "/" + description + "/" + by.format(formatter);
    }

}
