package cheese.task;

import cheese.CheeseException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Parent class for all tasks
 */
public class Task {
    private String name;
    private boolean done;

    /**
     * Simple constructor
     * @param name name for task
     * @throws CheeseException
     */
    public Task(String name) throws CheeseException {
        if (name.isBlank()) throw new CheeseException("Cheese needs to have a name");
        this.name = name;
        done = false;
    }

    /**
     * Takes in csv data (from Storage) that is split
     * @param data arr of String
     * @throws CheeseException
     */
    public Task(String[] data) throws CheeseException {
        if (data.length < 3) throw new CheeseException("Incorrect data format");
        done = Objects.equals(data[1], "1");
        name = data[2];
    }

    /**
     * Helper function for children to parse date
     * @param dateStr
     * @return
     * @throws CheeseException
     */
    public static LocalDate parseDate(String dateStr) throws CheeseException {
        LocalDate d;
        dateStr = dateStr.strip();
        try {
            d = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new CheeseException("Wrong format for date. YYYY-MM-DD");
        }
        return d;
    }

    /**
     * Setter to set task as done
     * @param done boolean
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Getter for name. For searching tasks by name
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * To display task in bot
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (done) {
            result.append("[X] ");
        } else {
            result.append("[ ] ");
        }
        result.append(name);
        return result.toString();
    }

    /**
     * To display task as csv to be saved be Storage
     * @return String
     */
    public String dataString() {
        String s = "T,";
        if (done) {
            s += "1,";
        } else {
            s += "0,";
        }
        s += name;
        return s;
    }
}
