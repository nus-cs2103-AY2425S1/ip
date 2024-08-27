import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate endTime;

    public Deadline(String desc, LocalDate endTime, boolean isDone) {
        super(desc, isDone);
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "D" + super.getSaveTaskString() + "|" + this.endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endTime + ")";
    }
}
