package Hamyo.Tasks;

import Hamyo.Misc.HamyoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has to be completed before a specified Deadline.
 *
 * @author Han Yu
 */
public class Deadline extends Task {

    private final LocalDate deadlineDate;
    private final LocalDateTime deadlineDateTime;

    /**
     * Constructor for Deadline instance.
     *
     * @param task Description for the task, stored in String array.
     *             [Description, Deadline].
     * @throws HamyoException If user inputs an invalid Date/ Time.
     */
    public Deadline(String[] task) throws HamyoException {
        super(task);
        try {
            LocalDateTime deadlineDateTimeTemp;
            try {
                deadlineDateTimeTemp = LocalDateTime.parse(task[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                deadlineDateTimeTemp = null;
            } finally {
                this.deadlineDate = LocalDate.parse(task[1].split(" ")[0]);
            }
            this.deadlineDateTime = deadlineDateTimeTemp;
        } catch (Exception e) {
            throw new HamyoException("Invalid date/time format. yyyy-MM-dd OR yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Converts the Deadline representation to a standardised format for the
     * printing of TaskList.
     *
     * @return Formatted String to represent the Deadline.
     */
    @Override
    public String toString() {
        String deadlineString = this.deadlineDateTime != null ?
            this.deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "HRS":
            this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D] " + super.toString() + " (by: " + deadlineString + ")";
    }

    /**
     * Converts the Deadline representation to a standardised format for the loading
     * and storing of tasks in files.
     *
     * @return Formatted String to represent the Deadline.
     */
    @Override
    public String toFileFormat() {
        String deadlineString = this.deadlineDateTime != null ?
            this.deadlineDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) :
            this.deadlineDate.toString();
        return "D" + " | " + super.toFileFormat() + " | " + deadlineString;
    }

    /**
     * Verify if the Deadline occurs on the specified date.
     *
     * @param  date The specified date to check.
     * @return true if Deadline falls on specified date, false otherwise.
     */
    @Override public boolean occursToday(LocalDate date) {
        return date.isEqual(this.deadlineDate);
    }
    
}
