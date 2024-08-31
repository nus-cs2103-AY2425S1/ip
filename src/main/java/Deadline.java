import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String taskInfo, String deadline) {
        super(taskInfo);
        this.deadline = LocalDateTime.parse(generateParse(deadline));
    }

    public Deadline(String taskInfo, LocalDateTime deadline) {
        super(taskInfo);
        this.deadline = deadline;
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
        String s = "[D] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return s;
    }

    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "D | " + i + " | " + this.getTaskInfo() + " | " + deadline;
    }
}
