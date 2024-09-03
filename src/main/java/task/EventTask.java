package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected LocalDate localDate;

    public EventTask(String description, String localDate) throws DateTimeParseException {
        super(description);
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.localDate = LocalDate.parse(localDate, dateFormatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public EventTask(boolean isDone, String description, String localDate) throws DateTimeParseException {
        super(description);
        this.isDone = isDone;
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.localDate = LocalDate.parse(localDate, dateFormatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
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