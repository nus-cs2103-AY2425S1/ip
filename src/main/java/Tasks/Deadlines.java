package Tasks;

import Exceptions.EmptyDeadlineDateException;
import Exceptions.EmptyDeadlineException;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Deadline class
public class Deadlines extends Task {

    private LocalDateTime localDateTime; // Stores full date and time
    private LocalDate localDate; // Stores date only
    private LocalTime localTime; // Stores time only
    private String rawDeadline; // Stores the raw string if it doesn't match any format

    /**
     * Calls constructor of super class
     * handles different potential formats of datetime input
     *
     * @param desc of task
     * @throws EmptyDeadlineException If desc is empty.
     * @throws EmptyDeadlineDateException if split desc string has < 2 parts(no date)
     * or second part(date) is empty
     */
    public Deadlines(String desc) throws EmptyDeadlineException, EmptyDeadlineDateException {
        super(desc);

        //throw exception if task desc not given
        if (desc.isEmpty()) {
            throw new EmptyDeadlineException
                    ("     OOPS!!! The description of a deadline cannot be empty leh. " +
                            "Pls provide in the following format: " +
                            "deadline read book /by yyyy-MM-dd or dd/MM/yyy 16:00");
        }

        //throw exception if date not given
        String[] parts = desc.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyDeadlineDateException
                    ("     OOPS!! Deadline date not given leh! " +
                            "Pls provide in the following format: " +
                            "deadline read book /by yyyy-MM-dd or dd/MM/yyy 16:00");
        }

        try {
            // input of format 2020-12-10 16:00
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            localDateTime = LocalDateTime.parse(parts[1], dateTimeFormatter);
        } catch (DateTimeParseException e1) {
            try {
                // input of format 2020-12-10
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                localDate = LocalDate.parse(parts[1], dateTimeFormatter);
            } catch (DateTimeParseException e2) {
                try {
                    // input of format 10/12/2020
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    localDate = LocalDate.parse(parts[1], dateTimeFormatter);
                } catch (DateTimeParseException e3) {
                    try {
                        // input of format 10/12/2020 16:00
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        localDateTime = LocalDateTime.parse(parts[1], dateTimeFormatter);
                    } catch (DateTimeParseException e4) {
                        try {
                            // input of format 16:00
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                            localTime = LocalTime.parse(parts[1], dateTimeFormatter);
                        } catch (DateTimeParseException e5) {
                            // if wrong format, just print the string
                            rawDeadline = parts[1];
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns string representation of deadline in word formats
     *
     */
    @Override
    public String print() {
        //splits string to get the due date
        String[] parts = super.print().split(" /by ");
        String dateTimeEdited = "";

        if (localDateTime != null) {
            // input of format 2020-12-10 16:00 || 10/12/2020 16:00 changed to Dec 12 2020, 4:00 pm
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            dateTimeEdited = localDateTime.format(dateTimeFormatter);
        } else if (localDate != null) {
            // input of format 2020-12-10 || 10/12/2020 changed to Dec 12 2020
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            dateTimeEdited = localDate.format(dateTimeFormatter);
        } else if (localTime != null) {
            // input of format 16:00 || changed to 4:00 pm
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
            dateTimeEdited = localTime.format(dateTimeFormatter);
        } else if (rawDeadline != null) {
            dateTimeEdited = rawDeadline;
        }
        return "[D]" + parts[0] + " (by: " + dateTimeEdited + ")";
    }

    /**
     * Returns date of deadline task in LocalDate type
     *
     */
    public LocalDate getLocalDate() {
        if (localDate != null) {
            return localDate;
        } else if (localDateTime != null) {
            return localDateTime.toLocalDate();
        } else {
            return null;
        }
    }
}

