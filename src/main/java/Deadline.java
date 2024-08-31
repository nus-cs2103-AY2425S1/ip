import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    protected String desc;
    protected String by;
    protected LocalDate date;
    protected String time;

    public Deadline(String description) {
        super(description);
        if (description.startsWith("deadline ")) {
            this.desc = description.split("deadline ")[1].split(" /by")[0];
            this.by = description.split("/by ")[1];
        } else if (description.startsWith("[D][ ] ")) {
            String[] parts = description.split("\\[D\\]\\[ \\] ");
            this.desc = parts[1].split(" \\(by:")[0];
            String[] byParts = parts[1].split("\\(by: ");
            this.by = byParts[1].split("\\)")[0];
            this.isDone = false;
        } else if (description.startsWith("[D][X] ")) {
            String[] parts = description.split("\\[D\\]\\[X\\] ");
            this.desc = parts[1].split(" \\(by:")[0];
            String[] byParts = parts[1].split("\\(by: ");
            this.by = byParts[1].split("\\)")[0];
            this.isDone = true;
        }
        String dateString = by.split(" ")[0];
        try {
            this.date = LocalDate.parse(dateString);
            SimpleDateFormat inputTime = new SimpleDateFormat("HHmm");
            SimpleDateFormat outputTime = new SimpleDateFormat("hh:mm a");
            Date time24 = inputTime.parse(by.split(" ")[1]);
            this.time = outputTime.format(time24);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + this.desc + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time + ")";
    }
}
