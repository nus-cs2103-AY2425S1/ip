package chatBot.task;

import chatBot.exception.EmptyDescException;

import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate fromTime;
    private final LocalDate toTime;

    // future: to check for toTime after fromTime

    public Event(String desc, String fromTime, String toTime) throws EmptyDescException {
        super(desc);
        this.fromTime = LocalDate.parse(fromTime);
        this.toTime = LocalDate.parse(toTime);
    }

    @Override
    public String getOriginalCommand() {
        return "event " + super.getOriginalCommand() + " /from "
                + this.fromTime.format(ORIGINALFORMAT)
                + " /to " + this.toTime.format(ORIGINALFORMAT);
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.fromTime.format(PRINTFORMAT) + ", to: "
                + this.toTime.format(PRINTFORMAT) + ")";
    }
}
