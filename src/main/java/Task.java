import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Task {
    private String name;
    private boolean done;

    Task(String name) throws CheeseException {
        if (name.isBlank()) throw new CheeseException("Cheese needs to have a name");
        this.name = name;
        done = false;
    }

    Task(String[] data) throws CheeseException {
        if (data.length < 3) throw new CheeseException("Incorrect data format");
        done = Objects.equals(data[1], "1");
        name = data[2];
    }

    public static LocalDate parseDate(String dateStr) throws CheeseException {
        LocalDate d;
        dateStr = dateStr.strip();
        try {
            d = LocalDate.parse(dateStr);
            System.out.println(LocalDate.now());
        } catch (DateTimeParseException e) {
            System.out.println(dateStr);
            throw new CheeseException("Wrong format for date. YYYY-MM-DD");
        }
        return d;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

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
