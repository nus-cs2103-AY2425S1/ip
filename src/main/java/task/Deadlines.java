package task;

import exception.CommandFoundButInvalidException;
import exception.EmptyDescriptionException;
import exception.InvalidSyntaxException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Deadlines extends Task {
//    private String deadline;
    private LocalDateTime deadline;
    private String initDesc;

    /**
     * Constructs a {@code Deadlines} object with the specified description
     *
     * @param description the description of the task, including the deadline
     * @throws CommandFoundButInvalidException if the description format is incorrect
     * @throws InvalidSyntaxException if the deadline is not in the correct format
     */
    public Deadlines(String description) throws CommandFoundButInvalidException {
        super(description);
        this.initDesc = description;
        super.description = this.getValidString(description)[0].trim();
        // restrict the usage to only "yyyy-mm-dd"
        // replace all the occurrence of "/" into "-"
        // check if is a valid LocalDate
        String date = this.getValidString(description)[1].trim();
        date = date.replace("/", "-");
        try {
            deadline = LocalDateTime.parse(date);
        } catch (DateTimeException e) {
            throw new InvalidSyntaxException("deadline, please use yyyy-mm-ddThh:mm. E.g. 2024-09-11T23:59");
        }
    }

    /**
     * Returns a strinf representation of the deadline task in a user-readable format
     *
     * @return a string representation of the deadline task
     */
    public String toString() {
        String str = " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm")) + ")";
        return "[D]" + super.toString() + str;
    }

    /**
     * Checks the input string and splits the string into the task description and deadline components
     *
     * @param description the description of the task, including the deadline
     * @return an array of string where the first element is the task description and the second is the
     *         deadline
     * @throws CommandFoundButInvalidException if the description format is incorrect
     */
    public String[] getValidString(String description) throws CommandFoundButInvalidException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] inputArray = description.split("/by");
        if (inputArray.length == 2 && !inputArray[0].isEmpty() && !inputArray[1].isEmpty()) {
            return inputArray;
        }
        throw new InvalidSyntaxException("deadline");
    }

    public String getInitDesc() {
        String str = super.isDone ? "1" : "0";
        return String.format("D | %s | %s", str, this.initDesc);
    }
}
