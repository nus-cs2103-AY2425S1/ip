package task.tasktype;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import task.Task;

public class Event extends Task {

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String[] startArr = start.split(" ");
        startDate = LocalDate.parse(startArr[0], formatter);
        if (startArr.length == 2) {
            startTime = LocalTime.parse(startArr[1]);
        }


        String[] endArr = start.split(" ");
        endDate = LocalDate.parse(endArr[0], formatter);
        if (endArr.length == 2) {
            endTime = LocalTime.parse(endArr[1]);
        }
    }

    @Override
    public String toString() {
        String altFormatStart = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String altFormatEnd = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String startString = startTime == null ? "" : startTime.toString();
        String endString = endTime == null ? "" : endTime.toString();
        String start = altFormatStart + " " + startString;
        String end = altFormatEnd + " " + endString;
        String dateStr = ("from: " + start + " to: " + end).trim();
        return "[E]" + super.toString() + " (" + dateStr + ")";
    }
}
