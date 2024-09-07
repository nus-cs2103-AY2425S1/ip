package spongebob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import spongebob.exception.SpongebobException;


/**
 * An Event task, contains a dateTime for start and enddate
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an instance of Event after checking the format of description, start and end dates.
     *
     * @param description description of task
     * @param startDate  start date (from)
     * @param endDate end date (to)
     * @throws SpongebobException   user input error such as incorrect date format
     */
    public Event(String description, String startDate, String endDate) throws SpongebobException {

        super(description, TaskType.EVENT);

        // checks if anything field is missing
        if (description.equals(" ") || startDate.equals(" ") || endDate.equals(" ")
                || description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {

            String msg = "";
            if (description.equals(" ") || description.isEmpty()) {
                msg += " Description,";
            }
            if (startDate.equals(" ") || startDate.isEmpty()) {
                msg += " From,";
            }
            if (endDate.equals(" ") || endDate.isEmpty()) {
                msg += " To";
            }

            throw new SpongebobException("Barnacles! You missed out " + msg + "!");
        }

        try {

            this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new SpongebobException("Barnacles! Please enter date at dd/mm/yyyy!");
        }
    }

    @Override
    public String toString() {

        return "[E]"
                + super.toString()
                + "(From: "
                + this.startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " To: "
                + this.endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy "))
                + ")"
                + super.getTag();

    }

    /**
     * Converts task into a string that can be stored and read later
     *
     * @return String version of the task
     */
    @Override
    public String save() {

        return super.save() + "|"
                + this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "|"
                + this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }
}