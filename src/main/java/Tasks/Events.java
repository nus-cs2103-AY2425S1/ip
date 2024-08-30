package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private final LocalDate startDate;
    private final String startTime;
    private final LocalDate endDate;
    private final String endTime;

    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (this.startDate.isBefore(today)) {
            throw new BrockException("Start date cannot be earlier than today!");
        }
        if (this.endDate.isBefore(today)) {
            throw new BrockException("End date cannot be earlier than today!");
        }
        if (!endDate.isAfter(startDate)) {
            throw new BrockException("End date must be after start date!");
        }
    }

    public Events(String description, String startDateString, String endDateString) throws BrockException {
        super(description);
        boolean isParseStartDateSuccessful = false;
        try {
            this.startTime = "";
            this.endTime = "";
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
            this.startTime = startTimeString;
            this.endTime = endTimeString;
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
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateFormatted = this.endDate
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "(from: " + startDateFormatted
                + "to: " + endDateFormatted
                + ")";
    }
}
