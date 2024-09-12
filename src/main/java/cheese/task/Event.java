package cheese.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cheese.Parser;
import cheese.exception.CheeseException;

/**
 * Task with 2 dates
 */
public class Event extends Deadline {
    private LocalDate endDate;

    /**
     * Creates an event
     * @param name name of Task
     * @param startDate start date of Event
     * @param endDate end date of Event
     * @throws CheeseException if name is blank
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) throws CheeseException {
        super(name, startDate);
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
        endDate = Parser.parseDate(data[4]);
    }

    @Override
    public long reschedule(LocalDate newDate) {
        long daysDelayed = super.reschedule(newDate);
        endDate = endDate.plusDays(daysDelayed);
        return daysDelayed;
    }

    @Override
    public void reschedule(long daysDelayed) {
        super.reschedule(daysDelayed);
        endDate = endDate.plusDays(daysDelayed);
    }

    /**
     * To display task in bot
     *
     * @return String
     */
    @Override
    public String toString() {
        String s = super.toString().replace("[D]", "[E]");
        s = s.replace("by: ", "").replace(")", "");
        s += "-" + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
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
        s = s.replace("D,", "E,");
        s += "," + endDate.toString();
        return s;
    }
}
