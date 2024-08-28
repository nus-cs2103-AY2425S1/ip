import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        try {
            return "[D] " + super.getName() + "(by " +
                    LocalDate.parse(this.endDate.substring(0, 10))
                            .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                    LocalTime.parse(this.endDate.substring(11, 13) + ":" + this.endDate.substring(13,15))
                            .format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } catch (Exception e) {
            return "[D] " + super.getName() + "(" + this.endDate + ")";
        }
    }
}