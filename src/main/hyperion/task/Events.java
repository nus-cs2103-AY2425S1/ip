package task;

import exception.CommandFoundButInvalidException;
import exception.EmptyDescriptionException;
import exception.InvalidSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Events extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String initDesc;
    public Events(String description) throws CommandFoundButInvalidException {
        super(description);
        this.initDesc = description;
        String[] inputs = this.getValidString(description);
        super.description = inputs[0];
        String start = inputs[1];
        String end = inputs[2];
        try {
            this.startDate = LocalDateTime.parse(start);
            this.endDate = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            throw new InvalidSyntaxException("event, please use yyyy-mm-ddThh:mm. E.g. 2024-09-11T23:59");
        }
    }
    public String toString() {
        String startString = startDate.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm"));
        String endString = endDate.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm"));
        String str = " (from: " + startString + " to: " + endString + ")";
        return "[E]" + super.toString() + str;
    }
    public String[] getValidString(String description) throws CommandFoundButInvalidException{
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String[] firstSplit = description.split("/from", 2);
        if (firstSplit.length == 2 && !firstSplit[0].isEmpty() && !firstSplit[1].isEmpty()) {
            String[] secondSplit = firstSplit[1].split("/to");
            if (secondSplit.length == 2 && !secondSplit[0].isEmpty() && !secondSplit[1].isEmpty()) {
                String desc = firstSplit[0].trim();
                String start = secondSplit[0].trim();
                String end = secondSplit[1].trim();
                return new String[]{desc, start, end};
            }
            throw new InvalidSyntaxException("event");
        }
        throw new InvalidSyntaxException("event");
    }

    public String message(List<Task> allTasks) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", allTasks.size());
        return str1 + this.toString() + "\n" + str2;
    }

    public String getInitDesc() {
        String str = super.isDone ? "1" : "0";
        return String.format("E | %s | %s", str, this.initDesc);
    }
}
