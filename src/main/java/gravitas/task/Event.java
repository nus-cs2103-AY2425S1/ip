package gravitas.task;

import gravitas.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String description, String startDate, String endDate) throws DukeException {
        super(description, "E");

        try {
            String[] startTimeArr = startDate.split(" ", 2);
            String[] EndTimeArr = endDate.split(" ", 2);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localStartDate = LocalDate.parse(startTimeArr[0], dateFormatter);
            LocalDate localEndDate = LocalDate.parse(EndTimeArr[0], dateFormatter);
            String formattedStartTime = startTimeArr[1].substring(0, 2) + ":" +
                    startTimeArr[1].substring(2, 4);
            String formattedEndTime = EndTimeArr[1].substring(0, 2) + ":" + EndTimeArr[1].substring(2, 4);
            this.startDate = localStartDate;
            this.endDate = localEndDate;
            this.startTime = LocalTime.parse(formattedStartTime);
            this.endTime = LocalTime.parse(formattedEndTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time.");
        }
    }

    @Override
    public String getDescription() {
        String formatStartDate = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hhmm a");
        return super.description + " (From: " + formatStartDate + " " +
                this.startTime.format(timeFormatter) + " To: " + formatEndDate + " " +
                this.endTime.format(timeFormatter) + ")";
    }

    @Override
    public String formatData() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String mark = this.isDone ? "1" : "0";
        return (this.eventType + " | " + mark + " | " + this.description +
                " | " + this.startDate.format(dateFormatter) + " | " +
                this.startTime.format(timeFormatter) + " | " +
                this.endDate.format(dateFormatter) + " | " +
                this.endTime.format(timeFormatter) + "\n");
    }
}
