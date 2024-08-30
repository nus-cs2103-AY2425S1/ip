package task;
import exception.MaxineException;
import java.util.ArrayList;

public class Event extends Task {

    protected String start;
    protected String end;
    public Event() {
        super();
    }

    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = dateTimeParser(start.trim());
            this.end = dateTimeParser(end.trim());
        } catch (Exception e) {
            this.start = start;
            this.end = end;
        }
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + description
                + " (From: " + start + " | To: " + end + ")";
    }

    @Override
    public String writeToFile() {
        return "E" + super.writeToFile() + " / " + start + " / " + end;
    }

}

