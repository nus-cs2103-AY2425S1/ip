package Task.TaskType;

import Task.TaskType.TaskType;

import Task.Task;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;


    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.println(by.trim());
        String[] deadlineArr = by.trim().split(" ");
        deadlineDate = LocalDate.parse(deadlineArr[0], formatter);
        if (deadlineArr.length == 2) {
            String timeStr = deadlineArr[1];
            if (timeStr.length() == 4) {
                timeStr = timeStr.substring(0, 2) + ":" + timeStr.substring(2);
            }
            deadlineTime = LocalTime.parse(timeStr);
        }
    }

    @Override
    public String toString() {
        String altFormat = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeStr = deadlineTime == null ? "" : deadlineTime.toString();
        String deadline = (altFormat + " " + timeStr).trim();
        return "[D]" + super.toString() + " (by: " + deadline + ")" ;
    }
}
