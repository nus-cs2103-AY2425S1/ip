package spongebob.task;

import spongebob.exception.SpongebobException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An Event task, contains a dateTime for start and enddate
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;
    
    /**
     * constructor for an event task, checks if description, start and end dates to be in correct format
     * @param description description of task
     * @param from  start date
     * @param to end date
     * @throws SpongebobException   user input error such as incorrect date format
     */
    public Event(String description, String from, String to) throws SpongebobException {

        super(description, TaskType.EVENT);

        // check for errors
        if (description.equals(" ") || from.equals(" ") || to.equals(" ")
                || description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            String msg = "";
            if (description.equals(" ") || description.isEmpty()) {
                msg += " Description,";
            }
            if (from.equals(" ") || from.isEmpty()) {
                msg += " From,";
            }
            if (to.equals(" ") || to.isEmpty()) {
                msg += " To";
            }

            throw new SpongebobException("Barnacles! You missed out " + msg + "!");
        }

        try {

            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new SpongebobException("Barnacles! Please enter date at dd/mm/yyyy!");
        }
    }

    @Override
    public String toString() {

        return "[E]"
                + super.toString()
                + "(from: "
                + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy "))
                + ")";

    }

    /**
     * converts task into a string that can be stored and read later
     * @return String version of the task
     */
    @Override
    public String save()  {

        return super.save() + "|"
                + this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "|"
                + this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }
}
