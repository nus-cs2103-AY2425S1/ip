package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime endTime;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.endTime = by;

    }

    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        }
        else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("deadline ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append(" /by ");
        saveTaskInfo.append(this.endTime.format(formatter));
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String date = this.endTime.format(formatter);
        return "[D] " + super.toString() + " (by: " + date + ")";
    }
}
