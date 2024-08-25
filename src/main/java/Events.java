import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Task{

    String from;
    String to;
    public Events(String name, int number, String from, String to) {
        super(name, number);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (completed ? 1 : 0) + " | " + name + " | " + from + " | " + to;
    }
}
