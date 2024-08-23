import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    private LocalDate startDate;
    private LocalDate endDate;

    private Event(String name, LocalDate startDate, LocalDate endDate) throws CheeseException {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Event(String[] data) throws CheeseException {
        super(data);
        if(data.length != 5) throw new CheeseException("Incorrect data format");
        startDate = Task.parseDate(data[3]);
        endDate = Task.parseDate(data[4]);
    }

    /**
     * Factory method to ensure correct creation of Event
     * @param input String
     * @return Event
     * @throws CheeseException custom exception
     */
    public static Event of(String input) throws CheeseException {
        String[] words = input.replace("event", "").strip().split("/from");
        if (words.length < 2) throw new CheeseException("Event needs /from .... /to");
        String[] dates = words[1].split("/to");
        if (dates.length < 2) throw new CheeseException("Event needs also needs a /to");
        return new Event(words[0].strip(), parseDate(dates[0].strip()), parseDate(dates[1].strip()));
    }

    public long daysLeft() {
        return LocalDate.now().until(startDate, ChronoUnit.DAYS);
    }

    @Override
    public String toString() {
        return "[E]" +
               super.toString() +
               " in " + daysLeft() + " days " +
               "(" +
                   startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "-" +
                   endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
               ")";
    }

    @Override
    public String dataString() {
        String s = super.dataString();
        s = s.replace("T,", "E,");
        s += "," + startDate.toString();
        s += "," + endDate.toString();
        return s;
    }
}
