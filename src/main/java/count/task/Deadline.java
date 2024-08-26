package count.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate endTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = LocalDate.parse(endTime, inputFormat);
    }

    public Deadline(String description, LocalDate endTime, boolean completion) {
        super(description, completion);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime.format(outputFormat) + ")";
    }
}
