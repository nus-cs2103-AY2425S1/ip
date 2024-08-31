package task.tasktype;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import task.Task;


public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String[] deadlineArr = by.split(" ");
        deadlineDate = LocalDate.parse(deadlineArr[0], formatter);
        if (deadlineArr.length == 2) {
            deadlineTime = LocalTime.parse(deadlineArr[1]);
        }
    }

    @Override
    public String toString() {
        String altFormat = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeStr = deadlineTime == null ? "" : deadlineDate.toString();
        String deadline = (altFormat + " " + timeStr).trim();
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
