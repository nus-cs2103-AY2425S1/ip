package alice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String saveString() {
        DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedTime = (by != null) ? by.format(saveFormatter) : "N/A";
        return "D" + super.saveString() + " | " + formattedTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedTime = (by != null) ? by.format(formatter)
                : "Fail to set deadline, check time format: dd-MM-yyyy HHmm";
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
