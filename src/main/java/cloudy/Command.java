package cloudy;

import java.time.LocalDate;

public class Command {
    private String type;
    private int taskNumber;
    private String taskDescription;
    private LocalDate deadline;
    private LocalDate startTime;
    private LocalDate endTime;

    public Command(String type) {
        this.type = type;
    }

    public Command(String type, int taskNumber) {
        this(type);
        this.taskNumber = taskNumber;
    }

    public Command(String type, String taskDescription) {
        this(type);
        this.taskDescription = taskDescription;
    }

    public Command(String type, String taskDescription, LocalDate deadline) {
        this(type, taskDescription);
        this.deadline = deadline;
    }

    public Command(String type, String taskDescription, LocalDate startTime, LocalDate endTime) {
        this(type, taskDescription);
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
