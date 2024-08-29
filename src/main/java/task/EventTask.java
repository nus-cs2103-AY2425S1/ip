package task;
import prince.Prince;


import java.time.LocalDateTime;

public class EventTask extends Task {
    protected String start;
    protected String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    // must make the description correctly
    @Override
    public String printTask() {
        return "[E]" + super.printTask() +  " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String printFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + start + " | " + end;
    }
}
