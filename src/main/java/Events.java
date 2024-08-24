import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Deadlines {
    protected LocalDate startTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");
    public Events(String description, String endTime, String startTime) {
        super(description, endTime);
        this.startTime = LocalDate.parse(startTime, inputFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.format(outputFormat) + " to: " + this.endTime.format(outputFormat) + ")";
    }
}
