package task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.InvalidDeadlineException;
import prince.Prince;



public class DeadlinesTask extends Task {
    protected LocalDateTime deadline;
    // need to create the formatter that will be the same for all the different dates and times throughout
    // hence the formatter needs to be static and final
    // but depends on whether time is given or not, so 2 different methods

    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public DeadlinesTask(String description, String deadline) throws InvalidDeadlineException {
        super(description);
        DateTimeFormatter toLocalDateTimeF = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.deadline = LocalDateTime.parse(deadline, toLocalDateTimeF);
            // if this doesnt work, prompt users to send the task correctly
        } catch(DateTimeParseException e) {
            throw new InvalidDeadlineException("Error saving task. You have given an invalid date-time format.\n" +
                    "Please use this format, yyyy-MM-dd HHmm.\n" + "An example is 2024-10-15 1800");
        }
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String getDeadlineToString(LocalDateTime time) {
        //toLocalTime() extracts time from datetime object
        //create default time
        if (deadline == null) {
            return "No deadline set";
        }
        LocalTime timeChecker = LocalDateTime.of(0, 1, 1, 0, 0).toLocalTime();
        if(time.toLocalTime() == timeChecker) {
            // means dont have time specified
            return time.format(dateFormatter);
        } else {
            return time.format(dateTimeFormatter);
        }
    }
    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + getDeadlineToString(this.deadline) + ")";
    } // this is for human readable string

    @Override
    public String printFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + this.description + " | " + getDeadlineToString(this.deadline);
    }

}



