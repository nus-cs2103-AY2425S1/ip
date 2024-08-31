import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String taskInfo, String start, String end) {
        super(taskInfo);
        this.start = LocalDateTime.parse(generateParse(start));
        this.end = LocalDateTime.parse(generateParse(end));
    }

    public Event(String taskInfo, LocalDateTime start, LocalDateTime end) {
        super(taskInfo);
        this.start = start;
        this.end = end;
    }

    private String generateParse(String deadline) {
        String[] dateAndTime = deadline.trim().split("\\s+|/");
        String parsedDate = dateAndTime[2] + "-" + dateAndTime[1] + "-" + dateAndTime[0];

        if (dateAndTime.length > 3) {
            String hour = dateAndTime[3].substring(0,2);
            String minute = dateAndTime[3].substring(2);
            parsedDate +=  "T" + hour + ":" + minute;
        } else {
            parsedDate += "T00:00:00";
        }
        return parsedDate;
    }

    @Override
    public String toString() {
        String s = "[E] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return s;
    }

    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "E | " + i + " | " + this.getTaskInfo() + " | " + start + " | " + end;
    }
}
