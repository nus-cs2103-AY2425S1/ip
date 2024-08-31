package talkabot.task;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import talkabot.Parser;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String[] details) {
        super(details[0]);
        this.deadline = Parser.stringToDate(details[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateToString(this.deadline) + ")";
    }

    public String fileString() {
        return super.fileString() + " | " + this.deadline;
    }

    public String getDay() {
        return this.deadline.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
