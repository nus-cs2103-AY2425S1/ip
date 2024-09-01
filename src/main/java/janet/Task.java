package janet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task
 */
public class Task {
    private final String description;
    private final String symbol;
    private boolean done;

    public Task(String description, String symbol) {
        this.description = description;
        this.done = false;
        this.symbol = symbol;
    }

    /**
     * Converts and returns the input date and time into appropriate formats
     * date -> MM dd yyyy
     * time -> hh:mm a
     *
     * @param inputDate A string representing a date in the format, yyyy-MM-dd
     * @param inputTime A string representing a time in the format, hh:mm
     * @return A string representing the time and date (MM dd yyyy hh:mm a)
     */
    public static String DateAndTimeFormatter(String inputDate, String inputTime) {
        LocalDate localDate = LocalDate.parse(inputDate);
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        LocalTime localTime = LocalTime.parse(inputTime);
        String time = localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return date + " " + time;
    }

    /**
     * @return task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return task's symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * @return the value of this.done, indicating whether the task is done (marked) or not done (unmarked)
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * @param newStatus assign newStatus (true/false) to this.done
     */
    public void setDone(boolean newStatus) {
        this.done = newStatus;
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[%s][X] %s", this.symbol, this.description);
        }
        return String.format("[%s][ ] %s", this.symbol, this.description);
    }
}
