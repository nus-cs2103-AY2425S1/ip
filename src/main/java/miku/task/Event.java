package miku.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
   private String dateTime1 = "";
   private String dateTime2 = "";
   LocalDateTime startDateTime;
   LocalDateTime endDateTime;

    public Event(String desc, String dateTime1, String dateTime2) {
        super(desc);
        this.dateTime1 = dateTime1.trim();
        this.dateTime2 = dateTime2.trim();
        startDateTime = LocalDateTime.parse(this.dateTime1);
        endDateTime = LocalDateTime.parse(this.dateTime2);

    }

    public int getStartYear(){
        return startDateTime.getYear();
    }

    public int getStartDayOfYear(){
        return startDateTime.getDayOfYear();
    }

    public Event(String desc, String dateTime1, String dateTime2, boolean isDone) {
        super(desc, isDone);
        this.dateTime1 = dateTime1.trim();
        this.dateTime2 = dateTime2.trim();
        startDateTime = LocalDateTime.parse(this.dateTime1);
        endDateTime = LocalDateTime.parse(this.dateTime2);
    }

    @Override
    public String stringValue() {
        return "[E]" + super.stringValue() + "(from: " + startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " to: " + endDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + ")";
    }

    public String storeValue() {
        return this.stringValue().substring(1,2) + " | " + this.isTaskDone() + " | " + this.getDesc() + " | " + dateTime1 + " | " + dateTime2 + "\n";
    }

}
