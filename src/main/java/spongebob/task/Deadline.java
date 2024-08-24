package spongebob.task;

import spongebob.exception.SpongebobException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A deadline task, contains a dateTime for due date
 */
public class Deadline extends Task {

    public String type;
    protected LocalDate by;

    /**
     * constructor for a deadline task, checks if description and deadline to be in correct format
     * @param description description of task
     * @param by    due date
     * @throws SpongebobException   user input error such as incorrect date format
     */
    public Deadline(String description, String by) throws SpongebobException {
        super(description, TaskType.DEADLINE);

        // check for errors
        if (description.equals(" ") || by.equals(" ")
                || description.isEmpty() || by.isEmpty()) {
            String msg = "";
            if (description.equals(" ") || description.isEmpty()) {
                msg += " Description,";
            }
            if (by.equals(" ") || by.isEmpty()) {
                msg += " By";
            }

            throw new SpongebobException("Barnacles! You missed out " + msg + "!");
        }

        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new SpongebobException("Barnacles! Please enter date at dd/mm/yyyy!");
        }

    }





    @Override
    public String toString() {

        return "[D]"
                + super.toString()
                + "(by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        // convert
    }

    /**
     * converts task into a string that can be stored and read later
     * @return String version of the deadline task
     */
    @Override
    public String save() {

        return super.save()
                + "|"
                + this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


    }
}
