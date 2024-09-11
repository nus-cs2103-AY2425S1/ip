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

    /**
     * Constructs a new {@code deadline} task with the specified description and deadline
     *
     * @param description A description of the deadline task
     * @param start date that the task needs to be finished by, given in {@code d/M/yyyy} format (e.g. 25/10/2015)
     * @param end
     */
    public Event(String description, String start, String end) {
        super(TaskType.EVENT, description);

        assert !start.isEmpty() : "every Event task must have a start date";
        assert !end.isEmpty() : "every Event task must have an end date";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String[] startArr = start.split(" ");
        startDate = LocalDate.parse(startArr[0], formatter);
        if (startArr.length == 2) {
            this.setTime(startArr[1], true);
        }

        String[] endArr = end.split(" ");
        endDate = LocalDate.parse(endArr[0], formatter);
        if (endArr.length == 2) {
            this.setTime(endArr[1], false);
        }
    }

    private void setTime(String timeStr, boolean isStartTime) {
        if (timeStr.length() == 4) {
            timeStr = timeStr.substring(0, 2) + ":" + timeStr.substring(2);
        }
        LocalTime parsedTime = LocalTime.parse(timeStr);

        if (isStartTime) {
            startTime = parsedTime;
        } else {
            endTime = parsedTime;
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
