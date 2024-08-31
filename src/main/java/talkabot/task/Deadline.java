package talkabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import talkabot.Parser;

/**
 * Deadline class contains task info and deadline of task.
 */
public class Deadline extends Task{
    private LocalDate deadline;

    /**
     * Constructor for Deadline class.
     */
    public Deadline(String[] details) {
        super(details[0]);
        this.deadline = Parser.stringToDate(details[1]);
    }

    /**
     * Returns string representation of deadline.
     *
     * @return Deadline description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateToString(this.deadline) + ")";
    }

    /**
     * Returns string representation of deadline
     * to be stored in save file.
     *
     * @return Deadline description.
     */
    public String fileString() {
        return super.fileString() + " | " + this.deadline;
    }

    /**
     * Returns deadline as day of the week.
     *
     * @return Deadline day.
     */
    public String getDay() {
        return this.deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
