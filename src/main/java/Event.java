import exception.EventStartEndDateEmptyException;
import exception.TaskNameEmptyException;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String taskName, String from, String to) throws TaskNameEmptyException, EventStartEndDateEmptyException {
        super(taskName);
        if (from.isBlank() || to.isBlank()) {
            throw new EventStartEndDateEmptyException();
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
