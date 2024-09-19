package Papagu.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.Locale;

/**
 * The Deadlines class represents a task with a description and a deadline(Time and Date)
 */
public class Deadlines extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for Deadline object
     * @param description
     * @param date
     * @param time
     */
    public Deadlines(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the string representation of task for saving to tasks.txt
     * @return string representation of task for saving to tasks.txt
     */
    @Override
    public String toFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy", Locale.ENGLISH);
        String formattedDate = this.date.format(formatter);

        return "D | " + (super.isDone() ? "1" : "0") + " | " 
                + super.getDescription() + " | "
                        + formattedDate + " " + this.time;
    }


    /**
     * Returns the string representation of the task for printing in Ui
     * @return string representation of task for printing in Ui
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = this.date.format(formatter);
        
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + this.time + ")";
    }
    
}
