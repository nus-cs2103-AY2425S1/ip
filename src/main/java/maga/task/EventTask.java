package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected LocalDate localDate;

    public EventTask(boolean isDone, String description, LocalDate localDate) {
        super(description);
        this.isDone = isDone;
        this.localDate = localDate;
    }

    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String toString() {
        int isDoneNum = 0;
        if (isDone) {
            isDoneNum = 1;
        }
        return "E | " + isDoneNum + " | " + description + " | " + localDate.toString();
    }

    @Override
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd");
        String formattedLocalDate = localDate.format(formatter);
        return this.getTaskType() + this.getStatusIcon() + this.getDescription() + " due on " + formattedLocalDate;
    }
}