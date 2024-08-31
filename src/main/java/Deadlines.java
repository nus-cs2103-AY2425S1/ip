import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

public class Deadlines extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadlines(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = this.date.format(formatter);

        return "D | " + (super.isDone() ? "1" : "0") + " | " 
                + super.getDescription() + " | " + formattedDate + " " + this.time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = this.date.format(formatter);
        
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + this.time + ")";
    }
    
}
