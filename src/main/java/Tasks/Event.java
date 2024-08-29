package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event (String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String startDate = this.startTime.format(formatter);
        String endDate = this.endTime.format(formatter);
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        }
        else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("event ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append(" /from ");
        saveTaskInfo.append(startDate);
        saveTaskInfo.append(" /to ");
        saveTaskInfo.append(endDate);
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String startDate = this.startTime.format(formatter);
        String endDate = this.endTime.format(formatter);
        return "[E] " + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
