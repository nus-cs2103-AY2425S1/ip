package gravitas.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String eventType;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected String startTime;
    protected String endTime;

    public Task(String description, String eventType, String startDate, String endDate) {
        this.description = description;
        this.isDone = false;
        this.eventType = eventType;

        //formatStartTime: [Date, startTime]
        String[] formatStartTime = startDate.split(" ", 2);
        String[] formatEndTime = endDate.split(" ", 2);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.println(formatStartTime[0]);
        LocalDate localStartDate = LocalDate.parse(formatStartTime[0], dateFormatter);
        LocalDate localEndDate = LocalDate.parse(formatEndTime[0], dateFormatter);
        this.startDate = localStartDate;
        this.endDate = localEndDate;
        this.startTime = formatStartTime[1];
        this.endTime = formatEndTime[1];
    }
    public abstract String getDescription();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getEventType() {
        return this.eventType;
    }
    public void markTask() {
        this.isDone = true;
    }
    public void unMarkTask() {
        this.isDone = false;
    }
    public String formatData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return (this.eventType + " | " + this.isDone + " | " + this.description +
                " | " + this.startDate.format(dateFormatter) + " | " + this.startTime +
                " | " + this.endDate.format(dateFormatter) + " | " + this.endTime + "\n");
    }
}