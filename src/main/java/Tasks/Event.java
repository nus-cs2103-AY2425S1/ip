package Tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String endTime;
    protected String startTime;
    protected LocalDate endTimeDate;
    protected LocalDate startTimeDate;

    public Event(String name, String startTime, String endTime) throws DateTimeParseException, DateTimeException {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
        try {
            this.startTimeDate = LocalDate.parse(startTime);
        } catch (DateTimeParseException parseException) {
            this.startTimeDate = LocalDate.parse(startTime,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            this.startTimeDate = LocalDate.parse(this.startTimeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        try {
            this.endTimeDate = LocalDate.parse(endTime);
        } catch (DateTimeParseException parseException) {
            this.endTimeDate = LocalDate.parse(endTime,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            this.endTimeDate = LocalDate.parse(this.endTimeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (endTimeDate.isBefore(startTimeDate)) {
            throw new DateTimeException("End time cannot be before start time");
        }
    }

    public String getDeadline() {
        return this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    @Override
    public void writeToFile(FileWriter fileWriter) {
        // Write event to file
        try {
            String toWrite = "E | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + " | " + this.startTime + " | " + this.endTime + "\n";
            fileWriter.write(toWrite);
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{E}" + super.toString() + " (from: " +
                this.startTimeDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + this.endTimeDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
