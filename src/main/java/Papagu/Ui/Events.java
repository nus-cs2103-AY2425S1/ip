package Papagu.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

/**
 * The Events class represents a task with a description, date, start time and end time
 */
public class Events extends Task {
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    /**
     * Constructor for Event object
     * @param description
     * @param date
     * @param start
     * @param end
     */
    public Events(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of task for saving to tasks.txt
     * @return
     */
    @Override
    public String toFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = this.date.format(formatter);

        return "E | " + (super.isDone() ? "1" : "0") + " | " 
                + super.getDescription() + " | " + formattedDate + " " + this.start + "-" + this.end;
    }

    /**
     * Returns the string representation of the task for printing in Ui
     * @return
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = this.date.format(formatter);

        return "[E]" + super.toString() 
                + " (from: " + formattedDate + " " + this.start + " to: " + this.end + ")";
    }
}
