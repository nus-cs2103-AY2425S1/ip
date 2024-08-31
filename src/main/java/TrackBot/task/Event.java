package TrackBot.task;

import TrackBot.task.Task;
import TrackBot.ui.Parser;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
        this.from = Parser.checkDateFormat(from);
        this.to = Parser.checkDateFormat(to);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + desc + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
