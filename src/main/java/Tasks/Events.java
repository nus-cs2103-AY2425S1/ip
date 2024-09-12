package Tasks;

import Exceptions.EmptyEventDateException;
import Exceptions.EmptyEventException;
import Exceptions.EmptyEventTimingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    private LocalDate localDate; // Stores date only
    private LocalTime localStartTime; // Stores time only
    private LocalTime localEndTime; // Stores time only
    private String rawDeadline; // Stores the raw string if it doesn't match any format

    /**
     * Calls constructor of super class
     *
     * @throws EmptyEventException       If desc is empty.
     * @throws EmptyEventTimingException if split desc string has < 2 parts(no time)
     *                                   or second part(start time) or third part(end time) is empty
     */
    public Events(String desc) throws EmptyEventException, EmptyEventTimingException, EmptyEventDateException {
        super(desc);
        // Split the description to extract the timings
        String[] parts1 = desc.split(" /from ");
        String[] parts2 = parts1[1].split(" /to ");
        String[] parts3 = parts2[1].split(" /on ");


        checkValidityOfEventInput(desc, parts1, parts2, parts3);

        parseEvent(parts2, parts3);
    }

    /**
     * Parses the event date and time from the provided description strings and
     * attempts to convert them into `LocalDate` and `LocalTime` objects.
     * If the first format fails, it will try a second format, and if that fails,
     * it stores the raw deadline string instead.
     *
     * @param parts2 The array containing the start and end time strings.
     * @param parts3 The array containing the end time and date strings.
     */
    private void parseEvent(String[] parts2, String[] parts3) {
        try {

            //input of format 16:00
            localStartTime = ParseTasks.parseTime(parts2[0]);
            localEndTime = ParseTasks.parseTime(parts3[0]);

            // input of format 2020-12-10
            localDate = ParseTasks.parseDateFormat1(parts3[1]);

        } catch (DateTimeParseException e1) {
            try {

                //input of format 16:00
                localStartTime = ParseTasks.parseTime(parts2[0]);
                localEndTime = ParseTasks.parseTime(parts3[0]);

                // input of format 10/12/2020
                localDate = ParseTasks.parseDateFormat2(parts3[1]);

            } catch (DateTimeParseException e2) {
                // if wrong format, just print the string
                rawDeadline = parts2[0];
            }
        }
    }

    /**
     * Validates the event description input by checking if the description,
     * start time, end time, and date are present and correctly formatted.
     * Throws exceptions if any part of the input is missing or invalid.
     *
     * @param desc   The original event description string.
     * @param parts1 The array containing the event description and start time.
     * @param parts2 The array containing the start time and end time.
     * @param parts3 The array containing the end time and date.
     *
     * @throws EmptyEventException       If the event description is empty.
     * @throws EmptyEventTimingException If the start or end time is missing or invalid.
     * @throws EmptyEventDateException   If the date is missing or invalid.
     */
    private static void checkValidityOfEventInput(String desc, String[] parts1, String[] parts2, String[] parts3) throws EmptyEventException, EmptyEventTimingException, EmptyEventDateException {
        if (desc.isEmpty()) {
            throw new EmptyEventException
                    ("     OOPS! Event start time not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }


        //throw exception if start time not given, or it is whitespace
        if (parts1.length < 2 || parts1[1].trim().isEmpty()) {
            throw new EmptyEventTimingException
                    ("     OOPS! Event start time not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }

        //throw exception if end time not given or it is whitespace
        if (parts2.length < 2 || parts2[1].trim().isEmpty()) {
            throw new EmptyEventTimingException
                    ("     OOPS! Event end time not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }


        //throw exception if date not given or it is whitespace
        if (parts3.length < 2 || parts3[1].trim().isEmpty()) {
            throw new EmptyEventDateException
                    ("     OOPS! Event date not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }
    }

    @Override
    public String print() {
        //splits string to get the timings and desc
        String[] parts1 = super.print().split(" /from ");
        String[] parts2 = parts1[1].split(" /to ");
        String[] parts3 = parts2[1].split(" /on ");

        String dateEdited = "";
        String startTimeEdited = "";
        String endTimeEdited = "";


        if (localDate != null && localStartTime != null && localEndTime != null) {
            // input of format /from 16:00 /to 18:00 /on 2020-12-10 || 10/12/2020 16:00 changed to Dec 12 2020, 4:00 pm to 6:00 pm
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            dateEdited = localDate.format(dateFormatter);

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
            startTimeEdited = localStartTime.format(timeFormatter);
            endTimeEdited = localEndTime.format(timeFormatter);

            return "[E]" + parts1[0] + " (from: " + startTimeEdited + " to: "
                    + endTimeEdited + " on: " + dateEdited + ")";

        } else if (rawDeadline != null) {
            dateEdited = rawDeadline;

            return "[E]" + parts1[0] + "( " + dateEdited + " )";
        }

        // Default return statement if none of the conditions are met
        return "[E]" + super.print();
    }

    /**
     * Returns date of event in LocalDate type
     *
     */
    public LocalDate getLocalDate() {
        if (localDate != null) {
            return localDate;
        } else {
            return null;
        }
    }
}
