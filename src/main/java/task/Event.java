package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.BrockException;

/**
 * Class representing an event task.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    /**
     * Sets the event task description, start and end date.
     * A dummy value is given for start and end time.
     * Sets the event task status to be uncompleted.
     *
     * @param description Task description.
     * @param startDateString Task start date.
     * @param endDateString Task end date.
     * @throws BrockException If start and end dates are not valid.
     */
    public Event(String description, String startDateString, String endDateString) throws BrockException {
        super(description);
        boolean isParseStartDateSuccessful = false;
        try {
            // Dummy values for time
            this.startTime = LocalTime.MAX;
            this.endTime = LocalTime.MAX;

            this.startDate = LocalDate.parse(startDateString);
            isParseStartDateSuccessful = true;
            this.endDate = LocalDate.parse(endDateString);

            this.validateDateTime();

        } catch (DateTimeParseException e) {
            if (isParseStartDateSuccessful) {
                throw new BrockException("End date string is not valid!");
            } else {
                throw new BrockException("Start date string is not valid!");
            }
        }
    }

    /**
     * Sets the event task description, start & end dates, start & end times.
     * Sets the event task status to be uncompleted.
     *
     * @param description Task description.
     * @param startDateString Task start date.
     * @param startTimeString Task start time.
     * @param endDateString Task end date.
     * @param endTimeString Task end time.
     * @throws BrockException If start & end datetimes are not valid.
     */
    public Event(String description, String startDateString, String startTimeString,
                 String endDateString, String endTimeString) throws BrockException {
        super(description);
        boolean isParseStartDateSuccessful = false;
        try {
            this.startTime = this.parseTime(startTimeString);
            this.endTime = this.parseTime(endTimeString);

            this.startDate = LocalDate.parse(startDateString);
            isParseStartDateSuccessful = true;
            this.endDate = LocalDate.parse(endDateString);

            this.validateDateTime();

        } catch (DateTimeParseException e) {
            if (isParseStartDateSuccessful) {
                throw new BrockException("End date string is not valid!");
            } else {
                throw new BrockException("Start date string is not valid!");
            }
        }
    }

    /**
     * Converts start/end time from {@code String} to {@code LocalTime}.
     *
     * @param timeString Time as a {@code String}.
     * @return Time as a {@code LocalTime}.
     */
    private LocalTime parseTime(String timeString) {
        String hours = timeString
                .substring(0, 2);
        String minutes = timeString
                .substring(2);
        return LocalTime.of(Integer.parseInt(hours),
                Integer.parseInt(minutes));
    }

    /**
     * Checks if the start/end date is valid.
     * If start/end time is provided (ie: not a dummy), checks if it is valid.
     *
     * @throws BrockException If they are not valid.
     */
    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (this.startDate.isBefore(today)) {
            throw new BrockException("Start date cannot be earlier than today!");
        }
        if (this.endDate.isBefore(today)) {
            throw new BrockException("End date cannot be earlier than today!");
        }
        if (this.endDate.isBefore(startDate)) {
            throw new BrockException("End date cannot be earlier than start date!");
        }

        if (this.startTime == LocalTime.MAX) {
            if (endDate.equals(startDate)) {
                throw new BrockException("Without time specified,"
                        + " end date must be after start date!");
            }
        } else {
            if (this.endDate.equals(startDate)
                    && !endTime.isAfter(startTime)) {
                throw new BrockException("End time must be after start time!");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtraInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String startDateFormatted = this.startDate
                .format(formatter);
        String endDateFormatted = this.endDate
                .format(formatter);
        return "(from: " + startDateFormatted
                + (this.startTime == LocalTime.MAX
                ? " | "
                : ", " + this.startTime.toString() + " | ")
                + "to: " + endDateFormatted
                + (this.endTime == LocalTime.MAX
                ? ""
                : ", " + this.endTime.toString())
                + ")";
    }
}
