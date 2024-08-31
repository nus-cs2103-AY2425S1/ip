import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {

    protected String desc;
    protected String from;
    protected String to;
    protected String fromDate;
    protected String fromTime;
    protected String toDate;
    protected String toTime;

    public Event(String description) {
        super(description);
        if (description.startsWith("event ")) {
            this.desc = description.split("event ")[1].split(" /from")[0];
            this.from = description.split("/from ")[1].split(" /to")[0];
            this.to = description.split("/to ")[1];
            String fromDateString = from.split(" ")[0];
            try {
                this.fromDate = LocalDate.parse(fromDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                SimpleDateFormat inputTime = new SimpleDateFormat("HHmm");
                SimpleDateFormat outputTime = new SimpleDateFormat("hh:mm a");
                Date time24 = inputTime.parse(from.split(" ")[1]);
                this.fromTime = outputTime.format(time24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String toDateString = to.split(" ")[0];
            try {
                this.toDate = LocalDate.parse(toDateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                SimpleDateFormat inputTime = new SimpleDateFormat("HHmm");
                SimpleDateFormat outputTime = new SimpleDateFormat("hh:mm a");
                Date time24 = inputTime.parse(to.split(" ")[1]);
                this.toTime = outputTime.format(time24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (description.startsWith("[E][ ] ")) {
            String[] parts = description.split("\\[E\\]\\[ \\] ");
            this.desc = parts[1].split(" \\(from:")[0];
            String[] fromParts = parts[1].split("\\(from: ");
            this.from = fromParts[1].split(" to:")[0];
            String[] toParts = fromParts[1].split(" to: ");
            this.to = toParts[1].split("\\)")[0];
            this.isDone = false;
            this.fromDate = from.split(", ")[0];
            this.fromTime = from.split(", ")[1];
            this.toDate = to.split(", ")[0];
            this.toTime = to.split(", ")[1];
        } else if (description.startsWith("[E][X] ")) {
            String[] parts = description.split("\\[E\\]\\[X\\] ");
            this.desc = parts[1].split(" \\(from:")[0];
            String[] fromParts = parts[1].split("\\(from: ");
            this.from = fromParts[1].split(" to:")[0];
            String[] toParts = fromParts[1].split(" to: ");
            this.to = toParts[1].split("\\)")[0];
            this.isDone = true;
            this.fromDate = from.split(", ")[0];
            this.fromTime = from.split(", ")[1];
            this.toDate = to.split(", ")[0];
            this.toTime = to.split(", ")[1];
        }

    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + this.desc + " (from: " +
                this.fromDate + ", " + this.fromTime + " to: " +
                this.toDate + ", " + this.toTime + ")";
    }
}
