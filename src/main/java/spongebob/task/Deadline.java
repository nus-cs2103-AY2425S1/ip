package spongebob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import spongebob.exception.SpongebobException;

/**
 * A deadline task, contains a dateTime for due date
 */
public class Deadline extends Task {

    protected LocalDate deadline;

    /**
     * Constructs a deadline task after checking description and deadline to be in correct format.
     *
     * @param description description of task
     * @param deadline    due date
     * @throws SpongebobException   user input error such as incorrect date format
     */
    public Deadline(String description, String deadline) throws SpongebobException {
        super(description, TaskType.DEADLINE);

        // check missing fields
        if (description.equals(" ") || deadline.equals(" ")
                || description.isEmpty() || deadline.isEmpty()) {

            String msg = "";
            if (description.equals(" ") || description.isEmpty()) {
                msg += " Description,";
            }
            if (deadline.equals(" ") || deadline.isEmpty()) {
                msg += " By";
            }

            throw new SpongebobException("Barnacles! You missed out " + msg + "!");
        }

        try {
            this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new SpongebobException("Barnacles! Please enter date at dd/mm/yyyy!");
        }
    }

    @Override
    public String toString() {

        return "[D]"
                + super.toString()
                + "(By: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")" + super.getTag();
        // convert
    }

    /**
     * Converts task into a string that can be stored and read later.
     *
     * @return String version of the deadline task
     */
    @Override
    public String save() {

        return super.save()
                + "|"
                + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


    }
}
