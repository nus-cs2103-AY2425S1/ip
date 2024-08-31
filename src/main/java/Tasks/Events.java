package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

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
        if (this.startDate.isBefore(today)) {
            throw new BrockException("Start date cannot be earlier than today!");
        }
        if (this.endDate.isBefore(today)) {
            throw new BrockException("End date cannot be earlier than today!");
        }
        if (this.endDate.isBefore(this.startDate)) {
            throw new BrockException("End date cannot be earlier than start date!");
        }

        if (this.startTime == LocalTime.MAX) {
            if (this.endDate.equals(this.startDate)) {
                throw new BrockException("Without time specified,"
                        + " end date must be after start date!");
            }
        } else {
            if (this.endDate.equals(this.startDate)
                    && !this.endTime.isAfter(this.startTime)) {
                throw new BrockException("End time must be after start time!");
            }
        }
    }

    public Events(String description, String startDateString, String endDateString) throws BrockException {
        super(description);
        boolean isParseStartDateSuccessful = false;
        try {
            // Dummy values for time
            this.startTime = LocalTime.MAX;
            this.endTime = LocalTime.MAX;

            this.startDate = LocalDate.parse(startDateString);
            isParseStartDateSuccessful = true;
            this.endDate = LocalDate.parse(endDateString);
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
            this.startTime = parseTime(startTimeString);
            this.endTime = parseTime(endTimeString);

            this.startDate = LocalDate.parse(startDateString);
            isParseStartDateSuccessful = true;
            this.endDate = LocalDate.parse(endDateString);
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
        String startDateFormatted = this.startDate
                .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String endDateFormatted = this.endDate
                .format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
