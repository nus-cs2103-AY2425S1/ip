import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected char eventType;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected String startTime;
    protected String endTime;

    public Task(String description, char eventType, String startDate, String endDate) {
        this.description = description;
        this.isDone = false;
        this.eventType = eventType;

        //formatStartTime: [Date, startTime]
        String[] formatStartTime = startDate.split(" ", 2);
        String[] formatEndTime = endDate.split(" ", 2);
        DateTimeFormatter input = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localStartDate = LocalDate.parse(formatStartTime[0], input);
        LocalDate localEndDate = LocalDate.parse(formatEndTime[0], input);
        this.startDate = localStartDate;
        this.endDate = localEndDate;
        this.startTime = formatStartTime[1];
        this.endTime = formatEndTime[1];
    }
    public abstract String getDescription();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public char getEventType() {
        return this.eventType;
    }
    public void markTask() {
        this.isDone = true;
    }
    public void unMarkTask() {
        this.isDone = false;
    }
}