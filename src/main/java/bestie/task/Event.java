package bestie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an instance of an event task.
 */
public class Event extends Task {

    protected String start;
    protected String end;
    private String formattedStartTime;
    private String formattedEndTime;

    private LocalDateTime parsedStart;
    private LocalDateTime parsedEnd;

    /**
     * Creates a new event task.
     *
     * @param description Description of the task.
     * @param start Start time of the task in the format YYYY-MM-DD HHMM.
     * @param end End time of the task in the format YYYY-MM-DD HHMM.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.parsedStart = LocalDateTime.parse(start, inputFormat);
        this.parsedEnd = LocalDateTime.parse(end, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        this.formattedStartTime = this.parsedStart.format(outputFormat);
        this.formattedEndTime = this.parsedEnd.format(outputFormat);
    }

    /**
     * Formats the task in a consistent format to be saved in the bestie.txt file.
     * Allows for easier retrieval and converting tasks stored in bestie.txt into the TaskList.
     *
     * @return Formatted tasks to be saved in bestie.txt.
     */
    @Override
    public String toSaveFormat() {
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // Store format: E | 0 | book event | 2pm | 5pm
        return "E | " + storeCompleted + " | " + this.description + " | " + this.start + " | " + this.end;
    }

    /**
     * Formats the task to be displayed on the console.
     *
     * @return String representation of the event task on the console.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }
}
