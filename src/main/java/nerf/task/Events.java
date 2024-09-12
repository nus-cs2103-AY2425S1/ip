package nerf.task;

import java.time.LocalDate;

import nerf.io.Parser;

/**
 * Event class
 */
public class Events extends Task {
    private final LocalDate  fromDate;
    private final LocalDate  toDate;

    public Events(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Events(String description, boolean isDone, LocalDate fromDate, LocalDate toDate, String priority) {
        super(description, isDone, priority);
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns format for saving to file.
     * 
     * @return string format of task.
     */
    @Override
    public String getSaveFormat() {
        String fromDate = Parser.dateToString(this.fromDate, Parser.DateFormatType.SAVE);
        String toDate = Parser.dateToString(this.toDate, Parser.DateFormatType.SAVE);
        return String.format("E | %s | %s | %s", super.getSaveFormat(), fromDate, toDate);
    }

    /**
     * Returns format for printing.
     * 
     * @return string format of task.
     */
    @Override
    public String toString() {
        String fromDate = Parser.dateToString(this.fromDate, Parser.DateFormatType.PRINT);
        String toDate = Parser.dateToString(this.toDate, Parser.DateFormatType.PRINT);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), fromDate, toDate);
    }
}