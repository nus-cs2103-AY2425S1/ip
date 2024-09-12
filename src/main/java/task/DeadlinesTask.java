package task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.InvalidDeadlineException;
import prince.Prince;

/**
 * Represents a task with a deadline.
 *
 * A DeadlinesTask is a type of task that includes a deadline, at a specific date and time
 * The class provides multiple methods to retrieve and save that information.
 */


public class DeadlinesTask extends Task {
    protected LocalDateTime deadline;
    // need to create the formatter that will be the same for all the different dates and times throughout
    // hence the formatter needs to be static and final
    // but depends on whether time is given or not, so 2 different methods

    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a DeadlinesTask with the specified description and deadline in a particular format
     * @param description
     * @param deadline
     * @throws InvalidDeadlineException
     */

    public DeadlinesTask(String description, String deadline) throws InvalidDeadlineException {
        super(description);
        DateTimeFormatter toLocalDateTimeF = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadline, toLocalDateTimeF);
            // if this doesnt work, prompt users to send the task correctly
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException("Error saving task. You have given an invalid date-time format.\n" +
                    "Please use this format, yyyy-MM-dd HHmm.\n" + "An example is 2024-10-15 1800");
        }
    }

    /**
     * Getter method for the deadline
     * @return deadline
     */

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Getter method for the deadline in string method
     * @param time
     * @return
     */

    public String getDeadlineToString(LocalDateTime time) {
        //toLocalTime() extracts time from datetime object
        //create default time
        if (deadline == null) {
            return "No deadline set";
        }

        LocalTime timeChecker = LocalDateTime.of(0, 1, 1, 0, 0).toLocalTime();
        if (time.toLocalTime() == timeChecker) {
            // means dont have time specified
            return time.format(dateFormatter);
        } else {
            return time.format(dateTimeFormatter);
        }
    }

    /**
     * Returns a string representation of the task in a human-readable format.
     *
     * @return String
     */

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + getDeadlineToString(this.deadline) + ")";
    } // this is for human readable string

    /**
     * Returns a string representation of the task in a file-storage format.
     *
     * @return String
     */

    @Override
    public String printFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + this.description + " | " + getDeadlineToString(this.deadline);
    }

}



