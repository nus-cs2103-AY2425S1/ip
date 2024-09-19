package janet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a Deadline, with a description, symbol and due date.
 */
public class Deadline extends ScheduledTask {
    private final LocalDateTime dueDate;

    /**
     * @param inputLine A String input from the user.
     * @throws JanetException If keywords are missing, incorrect date and time format, or due date is invalid.
     */
    Deadline(String inputLine) throws JanetException {
        // inside the program this will be called
        this(createDeadlineFromUser(inputLine).getDescription(),
                createDeadlineFromUser(inputLine).getSymbol(),
                createDeadlineFromUser(inputLine).getDueDateInDateTime());
    }

    /**
     * @param description The description of the deadline.
     * @param symbol The deadline symbol, D.
     * @param dueDate The due date of the deadline, in LocalDateTime.
     */
    Deadline(String description, String symbol, LocalDateTime dueDate) {
        // this is used inside the static method: createDeadlineCommand
        super(description, symbol, dueDate);    // dueDate is the scheduledDate
        this.dueDate = dueDate;
    }

    /**
     * @param textLine A String of text from the text file to create deadline.
     */
    Deadline(String[] textLine) {
        this(createDeadlineFromTxt(textLine).getDescription(),
                createDeadlineFromTxt(textLine).getSymbol(),
                createDeadlineFromTxt(textLine).getDueDateInDateTime());
    }


    /**
     * Returns a String array based on user's commands.
     * First element = a String of janet.Deadline's description.
     * Second element = a String of janet.Deadline's dueDate (MMM dd yyyy hh:mm a).
     * input date and time format must be "yyyy-mm-dd HH:MM"
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @return a String[], where first elem = janet.Deadline.description, second elem = janet.Deadline.dueDate.
     */
    public static String[] findDeadlineDetails(String[] commandDetails) throws JanetException {
        int indexOfBy = getIndexOfKeyword(commandDetails, "/by");
        if (indexOfBy == 0) {
            // when the keyword '/by' is not found in the command.
            throw new JanetException("WHOOPS! Missing/Wrong keywords for creating deadline...");
        }
        if (commandDetails.length > (indexOfBy + 2) + 1) {
            // when there are additional texts beyond the due date and time specified (eg. ... /by 2024-01-01 18:00 blah blah)
            throw new JanetException("WHOOPS! Ensure you only have a single due date and time provided!");
        }
        // get description of janet.Deadline
        String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfBy);
        String description = String.join(" ", descriptionArray);
        String dueDate = null;
        try {
            // converts due date from (yyyy-MM-dd) to (MM dd yyyy)
            // converts time from (hh:mm) to (hh:mm a)
            dueDate = ScheduledTask.DateAndTimeFormatter(commandDetails[commandDetails.length - 2],
                    commandDetails[commandDetails.length - 1]);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            // when either dueDate is not provided OR dueDate provided is in wrong format
            throw new JanetException("WHOOPS! Ensure that the due date is in the format: yyyy-MM-dd HH:mm (24hr)");
        }
        return new String[]{description, dueDate};
    }


    /**
     * Returns a Deadline object that was created from the user's command.
     * Based on the description and due date.
     *
     * @param inputLine User's command that was typed into the command line.
     * @return new Deadline object.
     */
    public static Deadline createDeadlineFromUser(String inputLine) throws JanetException {
        String[] commandDetails = inputLine.split(" ");
        String[] deadlineDetails = findDeadlineDetails(commandDetails);     // (description, MMM dd yyyy hh:mm a)
        String dateAndTimeString = deadlineDetails[1];    // MMM dd yyyy hh:mm a
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
        LocalDateTime dateAndTime = LocalDateTime.parse(dateAndTimeString, stringToDateTime);  // convert String to LocalDateTime
        return new Deadline(deadlineDetails[0], "D", dateAndTime);
    }

    /**
     * Returns a Deadline object that was created from the line of text, in .txt file saved.
     *
     * @param textLine A line of text from the .txt file saved.
     * @return A Deadline object.
     */
    public static Deadline createDeadlineFromTxt(String[] textLine) {
        String symbol = textLine[0].trim();
        String description = textLine[2].trim();
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
        LocalDateTime dueDate = LocalDateTime.parse(textLine[3].trim(), stringToDateTime);
        return new Deadline(description, symbol, dueDate);
    }

    /**
     * @return task's dueDate (deadline) as a String
     */
    public String getDueDate() {
        String time = this.dueDate.format(DateTimeFormatter.ofPattern("HH:mm a"));
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date + " " + time;
    }


    /**
     * @return task's dueDate in LocalDateTime.
     */
    public LocalDateTime getDueDateInDateTime() {
        return this.dueDate;
    }


    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.getDueDate());
    }
}
