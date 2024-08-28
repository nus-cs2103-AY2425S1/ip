package nerf.task;

/**
 * Event class
 */

import java.time.LocalDate;

import nerf.io.Parser;

public class Events extends Task{
    private final LocalDate  fromDate;
    private final LocalDate  toDate;

    public Events(String description,LocalDate fromDate, LocalDate toDate){
        super(description);
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Events(String description, boolean status, LocalDate fromDate, LocalDate toDate){
        super(description,status);
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Return format for saving to file.
     * 
     * @return string format of task.
     */
    @Override
    public String getSaveFormat(){
        String fromDate = Parser.dateToString(this.fromDate,true);
        String toDate = Parser.dateToString(this.toDate,true);
        return String.format("E | %s | %s | %s", super.getSaveFormat(),fromDate, toDate);
    }

    /**
     * Return format for printing.
     * 
     * @return string format of task.
     */
    @Override
    public String toString(){
        String fromDate = Parser.dateToString(this.fromDate,true);
        String toDate = Parser.dateToString(this.toDate,true);
        return String.format("[E]%s (from: %s to: %s)",super.toString(),fromDate, toDate);
    }
}