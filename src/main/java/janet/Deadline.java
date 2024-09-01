package janet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a Deadline, with a description, symbol and due date.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    Deadline(String inputLine) throws JanetException {
        // inside the program this will be called
        super(createDeadlineCommand(inputLine).getDescription(), createDeadlineCommand(inputLine).getSymbol());
        this.dueDate = createDeadlineCommand(inputLine).dueDate;
    }

    Deadline(String description, String symbol, LocalDateTime dueDate) {
        // this is used inside the static method: createDeadlineCommand
        super(description, symbol);
        this.dueDate = dueDate;
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
        int indexOfBy = 0;
        // first word in commandDetails must be deadline, so start from the i=1 word
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals("/by")) {
                indexOfBy = i;
            }
        }
        // get description of janet.Deadline
        String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfBy);
        String description = String.join(" ", descriptionArray);
        String dueDate = null;
        try {
            // converts due date from (yyyy-MM-dd) to (MM dd yyyy)
            // converts time from (hh:mm) to (hh:mm a)
            dueDate = Task.DateAndTimeFormatter(commandDetails[commandDetails.length - 2], commandDetails[commandDetails.length - 1]);
        } catch (DateTimeParseException e) {
            throw new JanetException("WHOOPS! Ensure that the due date is in the format: yyyy-MM-dd hh:mm (24hr)");
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
    public static Deadline createDeadlineCommand(String inputLine) throws JanetException {
        String[] commandDetails = inputLine.split(" ");
        String[] deadlineDetails = findDeadlineDetails(commandDetails);     // (description, MMM dd yyyy hh:mm a)
        String dateAndTimeString = deadlineDetails[1];    // MMM dd yyyy hh:mm a
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dateAndTime = LocalDateTime.parse(dateAndTimeString, stringToDateTime);  // format String to LocalDateTime
        return new Deadline(deadlineDetails[0], "D", dateAndTime);
    }


    /**
     * @return task's dueDate (deadline)
     */
    public String getDueDate() {
        String time = this.dueDate.format(DateTimeFormatter.ofPattern("HH:mm a"));
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date + " " + time;
    }


    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.getDueDate());
    }
}
