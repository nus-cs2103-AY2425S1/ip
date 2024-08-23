package cheese.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import cheese.CheeseException;

/**
 * Task with a dateline
 */
public class Deadline extends Task {
    private final LocalDate date;

    private Deadline(String name, LocalDate date) throws CheeseException {
        super(name);
        this.date = date;
    }

    /**
     * Takes in csv data (from Storage) that is split
     * @param data String array
     * @throws CheeseException incorrect date format
     */
    public Deadline(String[] data) throws CheeseException {
        super(data);
        if (data.length != 4) {
            throw new CheeseException("Incorrect data format");
        }
        date = parseDate(data[3]);
    }

    /**
     * Factory method to ensure correct creation of Cheese.Deadline
     * @param input String
     * @return Cheese.Deadline
     * @throws CheeseException custom exception
     */
    public static Deadline of(String input) throws CheeseException {
        String[] tokens = input.replace("deadline", "").strip().split("/by");
        if (tokens.length < 2) {
            throw new CheeseException("Cheese.Deadline needs a /by");
        }
        if (tokens[1].isBlank()) {
            throw new CheeseException("Cheese.Deadline needs a date!");
        }
        return new Deadline(tokens[0].strip(), Task.parseDate(tokens[1]));
    }

    /**
     * Helper function to calculate days from dateline
     * @return long
     */
    public long daysLeft() {
        return LocalDate.now().until(date, ChronoUnit.DAYS);
    }

    /**
     * To display task in bot
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " in " + daysLeft() + " days "
            + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * To display task as csv to be saved be Storage
     * @return String
     */
    @Override
    public String dataString() {
        String s = super.dataString();
        s = s.replace("T,", "D,");
        s += "," + date.toString();
        return s;
    }
}
