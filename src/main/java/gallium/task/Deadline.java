package gallium.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected String desc;
    protected String by;
    protected String date;
    protected String time;

    /**
     * Constructs a Deadline task with a description.
     * The description is parsed to extract the task's description, due date, and
     * time.
     * 
     * @param description The description of the Deadline task.
     * @throws ParseException         If there is an error parsing the time.
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public Deadline(String description) throws ParseException {
        super(description);
        try {
            if (description.startsWith("deadline ")) {
                this.desc = description.split("deadline ")[1].split(" /by")[0];
                this.by = description.split("/by ")[1];
                parseDateTime();
            } else {
                parse(description);
            }
        } catch (ParseException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    private void parse(String description) {
        String[] parts = {};
        if (description.startsWith("[D][X] ")) {
            parts = description.split("\\[D\\]\\[X\\] ");
            this.isDone = true;
        } else if (description.startsWith("[D][ ] ")) {
            parts = description.split("\\[D\\]\\[ \\] ");
            this.isDone = false;
        } else {
            throw new IllegalArgumentException("Invalid deadline format!! Please input your date/time in YYYY-MM-DD HHMM (24 hour) format!");
        }
        this.desc = parts[1].split(" \\(by:")[0];
        String[] byParts = parts[1].split("\\(by: ");
        this.by = byParts[1].split("\\)")[0];
        this.date = by.split(", ")[0];
        this.time = by.split(", ")[1];
    }

    /**
     * Returns a string representation of the Deadline task.
     * 
     * @return A string representation of the Deadline task in the format "[D][X/
     *         ][description] (by: date, time)".
     */
    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.desc + " (by: "
                + this.date + ", " + this.time + ")";
    }

    /**
     * Returns the due date of the Deadline task.
     * 
     * @return The date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the due time of the Deadline task.
     * 
     * @return The time.
     */
    public String getTime() {
        return this.time;
    }

    private void parseDateTime() throws ParseException {
        String dateString = by.split(" ")[0];
        this.date = LocalDate.parse(dateString).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        SimpleDateFormat inputTime = new SimpleDateFormat("HHmm");
        SimpleDateFormat outputTime = new SimpleDateFormat("hh:mm a");
        Date time24 = inputTime.parse(by.split(" ")[1]);
        this.time = outputTime.format(time24);
    }

    @Override
    public void setDesc(String message) {
        this.desc = message;
    }

    public void setBy(String message) throws ParseException {
        this.by = message;
        this.parseDateTime();
    }

}
