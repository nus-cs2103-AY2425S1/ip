package ava.task.Tasks;

import ava.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    //TODO: Error handling
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event: ");
        sb.append(super.toString());
        sb.append("[From :");
        sb.append(startTime.format(DateTimeFormatter.ISO_DATE_TIME));
        sb.append(" To : ");
        sb.append(endTime.format(DateTimeFormatter.ISO_DATE_TIME));
        sb.append("]");
        return sb.toString();
    }

}
