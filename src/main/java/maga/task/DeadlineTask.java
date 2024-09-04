package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    protected LocalDate from;
    protected LocalDate to;

    public DeadlineTask(String description, String from, String to) throws DateTimeParseException {
        super(description);
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.from = LocalDate.parse(from, dateFormatter);
            this.to = LocalDate.parse(to, dateFormatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public DeadlineTask(boolean isDone, String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.isDone = isDone;
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.from = LocalDate.parse(from, dateFormatter);
            this.to = LocalDate.parse(to, dateFormatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
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
        return "E | " + isDoneNum + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return this.getTaskType() + this.getStatusIcon() + this.getDescription() + " from " + formattedFrom +
                " to " + formattedTo;
    }
}
