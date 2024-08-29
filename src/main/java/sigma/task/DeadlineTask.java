package sigma.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime date;

    public DeadlineTask(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return date.format(formatter);
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusString(), getDesc(), getDate());
    }

}
