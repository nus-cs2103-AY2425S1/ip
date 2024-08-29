package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;
    private final String PATTERN = "MMM d yyyy HHmm";
    public Event(String desc, Boolean mark, LocalDateTime start, LocalDateTime end) throws MissingDescriptionException{
        super(desc, mark);
        this.start = start;
        this.end = end;
    }

    @Override
    public String printTask() {
        return String.format("[E]%s (from: %s to: %s)", super.printTask(),
                start.format(DateTimeFormatter.ofPattern(PATTERN)),
                end.format(DateTimeFormatter.ofPattern(PATTERN)));
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s", super.toFileFormat(),
                start,
                end);
    }
}
