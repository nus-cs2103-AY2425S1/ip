package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    protected LocalDate startDate;
    protected LocalDate endDate;

    public DeadlineTask(boolean isDone, String description, LocalDate startDate, LocalDate endDate) throws DateTimeParseException {
        super(description);
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String toString() {
        int isDoneNum = 0;
        if (isDone) {
            isDoneNum = 1;
        }
        return "E | " + isDoneNum + " | " + description + " | " + startDate + " | " + endDate;
    }

    @Override
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd");
        String formattedFrom = startDate.format(formatter);
        String formattedTo = endDate.format(formatter);
        return this.getTaskType() + this.getStatusIcon() + this.getDescription() + " from " + formattedFrom +
                " to " + formattedTo;
    }
}
