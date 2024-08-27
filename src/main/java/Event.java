import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    LocalDate formattedFrom;
    LocalDate formattedTo;
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        if (description.isEmpty() || description.equals("event")) {
            throw new DukeException("event", "OOPS!!! The description of a event shouldn't be empty!\n");
        }
        this.formattedFrom = LocalDate.parse(from);
        this.formattedTo = LocalDate.parse(to);
        this.from = from;
        this.to = to;
    }
    public String getString() {
        return "[E]" + super.getString() + " (from: " + formattedFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + formattedTo.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getStorageFormat() {
        String output = this.isDone ? "1" : "0";
        output += " event " + description + " /from " + this.from + " /to " + this.to + "\n";
        return output;
    }
}
