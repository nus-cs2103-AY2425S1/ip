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

    /**
     * Constructs a {@code Events} object with the specified description
     *
     * @param description the description of the event, including the start and end times
     * @throws CommandFoundButInvalidException if the description format is incorrect
     * @throws InvalidSyntaxException if the description is not empty but is of incorrect syntax
     */
    /
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

    /**
     * Returns a string representation of the event in a user redable format
     *
     * @return a string representation of the evnt
     */
    public String toString() {
        String startString = startDate.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm"));
        String endString = endDate.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm"));
        String str = " (from: " + startString + " to: " + endString + ")";
        return "[E]" + super.toString() + str;
    }

    /**
     * Checks the input and splits the description string into task description, start date, and end date
     *
     * @param description the description specified by the user
     * @return an array where the first element is the task description, the second is the start string,
     *         the thrid is the end string
     * @throws CommandFoundButInvalidException if there is an error with the syntax of the description
     * @throws EmptyDescriptionException if the description string is empty
     * @throws InvalidSyntaxException if the description is not empty but there is error with the syntax
     */
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

    public String getInitDesc() {
        String str = super.isDone ? "1" : "0";
        return String.format("E | %s | %s", str, this.initDesc);
    }
}
