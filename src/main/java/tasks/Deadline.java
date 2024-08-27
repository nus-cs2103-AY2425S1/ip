package tasks;

import enums.TaskType;
import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + this.deadline.toString() + ")"; 
    }

    @Override
    public String getSaveFormat() {
        return this.description + " | " + this.deadline.toString(); 
    }
}
