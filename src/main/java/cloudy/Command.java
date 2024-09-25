package cloudy;

import java.time.LocalDate;

/**
 * Represents a command in the Cloudy application.
 * Provides methods to initialise a new instance of a command and to get task information
 */
public class Command {
    private String type;
    private int taskNumber;
    private String taskDescription;
    private LocalDate deadline;
    private LocalDate startTime;
    private LocalDate endTime;

    public Command(String type) {
        assert type != null : "Type should not be null";
        this.type = type;
    }

    public Command(String type, int taskNumber) {
        this(type);
        this.taskNumber = taskNumber;
    }

    public Command(String type, String taskDescription) {
        this(type);
        assert taskDescription != null : "Task description should not be null";
        this.taskDescription = taskDescription;
    }

    public Command(String type, String taskDescription, LocalDate deadline) {
        this(type, taskDescription);
        assert deadline != null : "Deadline should not be null";
        this.deadline = deadline;
    }

    public Command(String type, String taskDescription, LocalDate startTime, LocalDate endTime) {
        this(type, taskDescription);
        assert startTime != null : "Start time should not be null";
        assert endTime != null : "End time should not be null";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }


}
