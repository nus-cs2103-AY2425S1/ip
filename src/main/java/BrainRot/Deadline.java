package BrainRot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String end;

    /**
     *sdfasfdas 
     */

    public Deadline(String command, String end) {
        super(command);  // Pass task description to BrainRot.Task class

        // Define the correct formatter that matches "Dec 02 2019 18:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        // Parse the input string using the correct formatter
        LocalDateTime endDate = LocalDateTime.parse(end.trim(), formatter);

        // Store the formatted date as a string in the desired format
        this.end = endDate.format(formatter);
    }

    public Deadline(String command, String end, boolean fromFile) {
        super(command);
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "[D][" + (isDone ? "X" : " ") + "]/" + description + "/" + end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";  // Ensure the end time is displayed
    }
}
