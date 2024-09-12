package cheese.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import cheese.Parser;
import cheese.exception.CheeseException;

/**
 * Task with a dateline.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Creates Deadline from name and LocalDate.
     *
     * @param name of Task.
     * @param date for Deadline.
     * @throws CheeseException if name is blank.
     */
    public Deadline(String name, LocalDate date) throws CheeseException {
        super(name);
        this.date = date;
    }

    /**
     * Create Deadline from csv data (from Storage) that is split.
     *
     * @param data String array.
     * @throws CheeseException incorrect date format.
     */
    public Deadline(String[] data) throws CheeseException {
        super(data);
        if (data.length < 4) {
            throw new CheeseException("Incorrect data format");
        }
        date = Parser.parseDate(data[3]);
    }

    /**
     * Returns no. of days from dateline.
     */
    public long daysLeft() {
        return LocalDate.now().until(date, ChronoUnit.DAYS);
    }

    /**
     * Changes date of Deadline, returns no. of days delayed.
     *
     * @param newDate LocalDate.
     * @return no. of days delayed.
     */
    public long reschedule(LocalDate newDate) {
        long daysDelayed = date.until(newDate, ChronoUnit.DAYS);
        date = newDate;
        return daysDelayed;
    }

    /**
     * Changes date of Deadline, returns nothing.
     *
     * @param daysDelayed no. of days to delay.
     */
    public void reschedule(long daysDelayed) {
        date = date.plusDays(daysDelayed);
    }

    /**
     * To display task in bot.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " in " + daysLeft() + " days "
            + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * To display task as csv to be saved be Storage.
     *
     * @return String.
     */
    @Override
    public String dataString() {
        String s = super.dataString();
        s = s.replace("T,", "D,");
        s += "," + date.toString();
        return s;
    }
}
