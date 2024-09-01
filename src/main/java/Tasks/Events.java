package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private final LocalDate START_DATE;
    private final LocalTime START_TIME;
    private final LocalDate END_DATE;
    private final LocalTime END_TIME;

    private LocalTime parseTime(String timeString) {
        String hours = timeString
                .substring(0, 2);
        String minutes = timeString
                .substring(2);
        return LocalTime.of(Integer.parseInt(hours)
                , Integer.parseInt(minutes));
    }

    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (START_DATE.isBefore(today)) {
            throw new BrockException("Start date cannot be earlier than today!");
        }
        if (END_DATE.isBefore(today)) {
            throw new BrockException("End date cannot be earlier than today!");
        }
        if (END_DATE.isBefore(START_DATE)) {
            throw new BrockException("End date cannot be earlier than start date!");
        }

        if (START_TIME == LocalTime.MAX) {
            if (END_DATE.equals(START_DATE)) {
                throw new BrockException("Without time specified,"
                        + " end date must be after start date!");
            }
        } else {
            if (END_DATE.equals(START_DATE)
                    && !END_TIME.isAfter(START_TIME)) {
                throw new BrockException("End time must be after start time!");
            }
        }
    }

    public Events(String description, String startDateString, String endDateString) throws BrockException {
        super(description);
        boolean isParseStartDateSuccessful = false;
        try {
            // Dummy values for time
            START_TIME = LocalTime.MAX;
            END_TIME = LocalTime.MAX;

            START_DATE = LocalDate.parse(startDateString);
            isParseStartDateSuccessful = true;
            END_DATE = LocalDate.parse(endDateString);
            validateDateTime();

        } catch (DateTimeParseException e) {
            if (isParseStartDateSuccessful) {
                throw new BrockException("End date string is not valid!");
            } else {
                throw new BrockException("Start date string is not valid!");
            }
        }
    }

    public Events(String description, String startDateString, String startTimeString
            , String endDateString, String endTimeString) throws BrockException {
        super(description);
        boolean isParseStartDateSuccessful = false;
        try {
            START_TIME = parseTime(startTimeString);
            END_TIME = parseTime(endTimeString);

            START_DATE = LocalDate.parse(startDateString);
            isParseStartDateSuccessful = true;
            END_DATE = LocalDate.parse(endDateString);
            validateDateTime();

        } catch (DateTimeParseException e) {
            if (isParseStartDateSuccessful) {
                throw new BrockException("End date string is not valid!");
            } else {
                throw new BrockException("Start date string is not valid!");
            }
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public String getExtraInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String startDateFormatted = START_DATE
                .format(formatter);
        String endDateFormatted = END_DATE
                .format(formatter);
        return "(from: " + startDateFormatted
                + (START_TIME == LocalTime.MAX
                ? " | "
                : ", " + START_TIME.toString() + " | ")
                + "to: " + endDateFormatted
                + (END_TIME == LocalTime.MAX
                ? ""
                : ", " + END_TIME.toString())
                + ")";
    }
}
