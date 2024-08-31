package miku.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String date = "";
    LocalDate localDate;

    public Deadline(String desc, String date) {
        super(desc);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    public Deadline(String desc, String date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    public String storeValue() {
        return this.stringValue().substring(1, 2) + " | " + this.isTaskDone() + " | " + this.getDesc() + " | " + date + "\n";
    }

    @Override
    public String stringValue() {
        return "[D]" + super.stringValue() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + " )";
    }

    public int getStartYear() {
        return localDate.getYear();
    }

    public int getStartDayOfYear() {
        return localDate.getDayOfYear();
    }
}
