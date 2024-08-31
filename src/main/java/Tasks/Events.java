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

        if (desc.isEmpty()) {
            throw new EmptyEventException
                    ("     OOPS! Event start time not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }

        // Split the description to extract the timings
        String[] parts1 = desc.split(" /from ");

        //throw exception if start time not given, or it is whitespace
        if (parts1.length < 2 || parts1[1].trim().isEmpty()) {
            throw new EmptyEventTimingException
                    ("     OOPS! Event start time not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }

        String[] parts2 = parts1[1].split(" /to ");

        //throw exception if end time not given or it is whitespace
        if (parts2.length < 2 || parts2[1].trim().isEmpty()) {
            throw new EmptyEventTimingException
                    ("     OOPS! Event end time not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }

        String[] parts3 = parts2[1].split(" /on ");

        //throw exception if date not given or it is whitespace
        if (parts3.length < 2 || parts3[1].trim().isEmpty()) {
            throw new EmptyEventDateException
                    ("     OOPS! Event date not given leh. " +
                            "Pls provide in the following format: " +
                            "event project meeting /from 16:00 /to 18:00 /on yyyy-MM-dd or dd/MM/yyyy");
        }

        try {

            //input of format 16:00
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            localStartTime = LocalTime.parse(parts2[0], timeFormatter);
            localEndTime = LocalTime.parse(parts3[0], timeFormatter);

            // input of format 2020-12-10
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(parts3[1], dateFormatter);
        } catch (DateTimeParseException e1) {
            try {
                //input of format 16:00
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                localStartTime = LocalTime.parse(parts2[0], timeFormatter);
                localEndTime = LocalTime.parse(parts3[0], timeFormatter);

                // input of format 10/12/2020
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(parts3[1], dateFormatter);

            } catch (DateTimeParseException e2) {
                // if wrong format, just print the string
                rawDeadline = parts2[0];
            }
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
