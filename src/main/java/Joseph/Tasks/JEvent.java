package Joseph.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class JEvent extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public JEvent (String desc, String start, String end) {
        super(desc);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("dd/MM/yyyy HHmm"));
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String getDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.getDesc() +
                " start: " + this.start.format(formatter) +
                " end: "  + this.end.format(formatter);
    }
}
