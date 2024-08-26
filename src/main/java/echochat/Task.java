package echochat;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task implements Serializable {
    private Boolean done;
    private String desc;
    private Category taskType;

    private enum Category {
        T,
        D,
        E
    }

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    public Task(char c, String desc) {
        this.desc = desc;
        this.done = false;
        if (c == 'T') {
            this.taskType = Category.T;
        } else if (c == 'D') {
            this.taskType = Category.D;
        } else if (c == 'E') {
            this.taskType = Category.E;
        }
    }

    /**
     * Marks this task as done/completed.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks this task as not done/completed.
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * Returns description of task.
     * @return task description
     */
    public String getDesc() {
        return done.equals(true) ? "[" + taskType.name() + "]"+ "[X] " + this.desc
                                 : "[" + taskType.name() + "]"+ "[ ] " + this.desc;
    }

    // Method to parse the input date and time string, accepting both formats
    public LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            // Try to parse the input as date-time
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException e1) {
            try {
                // If date-time parsing fails, try parsing as date only
                LocalDate date = LocalDate.parse(dateTimeStr, dateFormatter);
                return date.atTime(LocalTime.MIDNIGHT); // Default to midnight (00:00)
            } catch (DateTimeParseException e2) {
                return null; // or handle error appropriately
            }
        }
    }

}
