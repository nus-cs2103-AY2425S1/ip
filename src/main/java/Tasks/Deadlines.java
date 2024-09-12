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

        parseDeadline(parts);
    }

    /**
     * Parses the deadline date and time from the provided description string and
     * attempts to convert it into `LocalDateTime`, `LocalDate`, or `LocalTime` objects.
     * If the first format fails, it will try other formats until it either succeeds
     * or stores the raw deadline string.
     *
     * @param parts The array containing the task description and deadline string.
     */
    private void parseDeadline(String[] parts) {
        try {
            // input of format 2020-12-10 16:00
            localDateTime = ParseTasks.parseDateTimeFormat1(parts[1]);

        } catch (DateTimeParseException e1) {
            try {
                // input of format 2020-12-10
                localDate = ParseTasks.parseDateFormat1(parts[1]);

            } catch (DateTimeParseException e2) {
                try {
                    // input of format 10/12/2020
                    localDate = ParseTasks.parseDateFormat2(parts[1]);

                } catch (DateTimeParseException e3) {
                    try {
                        // input of format 10/12/2020 16:00
                        localDateTime = ParseTasks.parseDateTimeFormat2(parts[1]);

                    } catch (DateTimeParseException e4) {
                        try {
                            // input of format 16:00
                            localTime = ParseTasks.parseTime(parts[1]);

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

