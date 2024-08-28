import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected String start;
    protected String end;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HHmm MMM dd yyyy");
    public Event(String taskName, String timeframe) throws Meowception {
        super(taskName);
        this.start= timeframe.substring(timeframe.indexOf("/from") + 6, timeframe.indexOf("/to"));
        this.end = timeframe.substring(timeframe.indexOf("/to") + 4);
        try {
            startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (java.time.format.DateTimeParseException e) {
            throw new Meowception("300");
        }
    }
    @Override
    public String toString() {
        
        String startFormated = startDateTime.format(FORMATTER);
        String endFormated = endDateTime.format(FORMATTER);
        return "[E]" + super.toString() + " (at: " + startFormated + " to: "+ endFormated + ")";
    }

    @Override
    public String getExtra() {
        return "/from " + startDateTime.toString() + " /to " + endDateTime.toString();
    }

    @Override
    public String getType() {
        return "event";
    }

}
