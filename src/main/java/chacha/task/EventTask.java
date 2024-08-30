package chacha.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDate date;
    protected String startTime;
    protected String endTime;

    public EventTask(String description, boolean isDone, LocalDate date, String startTime, String endTime) {
        super(description, isDone);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTask() {
        String output = "[E]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description +
                " (" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String writeTask() {
        String output = "E | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + " | " + date.toString() +
                " | " + startTime + "-" + endTime + "\n";
    }
}
