package cheese.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import cheese.CheeseException;

/**
 * Task with 2 dates
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private Event(String name, LocalDate startDate, LocalDate endDate) throws CheeseException {
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
        startDate = Task.parseDate(data[3]);
        endDate = Task.parseDate(data[4]);
    }

    /**
     * Factory method to ensure correct creation of Cheese.Event
     *
     * @param input String
     * @return Cheese.Event
     * @throws CheeseException custom exception
     */
    public static Event of(String input) throws CheeseException {
        String[] words = input.replace("event", "").strip().split("/from");
        if (words.length < 2) {
            throw new CheeseException("Cheese.Event needs /from .... /to");
        }
        String[] dates = words[1].split("/to");
        if (dates.length < 2) {
            throw new CheeseException("Cheese.Event needs also needs a /to");
        }
        return new Event(words[0].strip(), parseDate(dates[0].strip()), parseDate(dates[1].strip()));
    }

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
        return "[E]" + super.toString() + " in " + daysLeft() + " days " +
                   "(" +
                   startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "-" +
                   endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                   ")";
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
