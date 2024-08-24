import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate endTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");
    public Deadlines(String description, String endTime) {
        super(description);
        this.endTime = LocalDate.parse(endTime, inputFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime.format(outputFormat) + ")";
    }
}
