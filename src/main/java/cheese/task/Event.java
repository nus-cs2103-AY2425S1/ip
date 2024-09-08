package cheese.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import cheese.Parser;
import cheese.exception.CheeseException;

/**
 * Task with 2 dates
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates an event
     * @param name name of Task
     * @param startDate start date of Event
     * @param endDate end date of Event
     * @throws CheeseException if name is blank
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) throws CheeseException {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Takes in csv data (from Storage) that is split
     *
     * @param data arr of String
     * @throws CheeseException incorrect date
     */
    public Event(String[] data) throws CheeseException {
        super(data);
        if (data.length != 5) {
            throw new CheeseException("Incorrect data format");
        }
        startDate = Parser.parseDate(data[3]);
        endDate = Parser.parseDate(data[4]);
    }

    /**
     * Calculate days till deadline
     * @return int in days
     */
    public long daysLeft() {
        return LocalDate.now().until(startDate, ChronoUnit.DAYS);
    }

    /**
     * To display task in bot
     *
     * @return String
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString() + " in " + daysLeft() + " days ";
        s += "(" + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "-";
        s += endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return s;
    }

    /**
     * To display task as csv to be saved be Storage
     *
     * @return String
     */
    @Override
    public String dataString() {
        String s = super.dataString();
        s = s.replace("T,", "E,");
        s += "," + startDate.toString();
        s += "," + endDate.toString();
        return s;
    }
}
