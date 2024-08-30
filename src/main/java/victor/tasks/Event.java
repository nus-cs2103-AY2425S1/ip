package victor.tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class that handles tasks with a start time and an end time.
 */
public class Event extends Task {
    protected String endTime;
    protected String startTime;
    protected LocalDate endTimeDate;
    protected LocalDate startTimeDate;

    /**
     * Constructor for the Event class that takes in strings for the event name, the start time,
     * and the end time.
     * @param name A string with the name of the event to be stored
     * @param startTime A string with the start date of the event, should be either in format yyyy-mm-dd or dd-mm-yyyy.
     * @param endTime A string with the end date of the event, should be either in format yyyy-mm-dd or dd-mm-yyyy.
     * @throws DateTimeParseException Thrown if the start or end time cannot be parsed as a LocalDate object.
     * @throws DateTimeException Thrown if the start time is after the end time.
     */
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
    public void writeToFile(FileWriter fileWriter) throws IOException {
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
    public void writeToFile(Path filePath) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(filePath), true);
            String toWrite = "E | ";
            if (this.isDone) {
                toWrite += "1 | ";
            } else {
                toWrite += "0 | ";
            }
            toWrite += this.name + " | " + this.startTime + " | " + this.endTime + "\n";
            fileWriter.write(toWrite);
            fileWriter.close();
        } catch (IOException writeException) {
            throw new RuntimeException(writeException);
        }
    }

    @Override
    public String toString() {
        return "{E}" + super.toString() + " (from: "
                + this.startTimeDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.endTimeDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
