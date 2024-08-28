import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    protected LocalDate time;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateObj = LocalDate.parse(by, inputFormatter);
        this.time = dateObj;
    }

    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[D]%s(by: %s)",super.toString(),this.time.format(outputFormatter));
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (isDone) {
            return String.format("D | %d | %s | %s",1,this.getDescription(),this.time.format(outputFormatter));
        } else {
            return String.format("D | %d | %s | %s",0,this.getDescription(),this.time.format(outputFormatter));
        }
    }
}
