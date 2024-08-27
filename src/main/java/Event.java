import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event (String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String startDate = this.start.format(formatter);
        String endDate = this.end.format(formatter);
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
        String startDate = this.start.format(formatter);
        String endDate = this.end.format(formatter);
        return "[E] " + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
