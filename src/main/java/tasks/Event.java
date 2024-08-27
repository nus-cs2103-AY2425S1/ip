package tasks;

import enums.TaskType;
import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String getDescription() {
        return this.description + " (from: " + this.startDate.toString() + " to: " + this.endDate.toString() + ")";
    }

    @Override
    public String getSaveFormat() {
        return this.description + " | " + this.startDate.toString() + " | " + this.endDate.toString(); 
    }
}
