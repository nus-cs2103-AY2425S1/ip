import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Task {
    private LocalDateTime startOfEvent;
    private LocalDateTime endOfEvent;

    public Events(String description, String startOfEvent, String endOfEvent) {
        super(description);
        this.startOfEvent = LocalDateTime.parse(startOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        this.endOfEvent = LocalDateTime.parse(endOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    public Events(String description, String startOfEvent, String endOfEvent, String done) {
        super(description);
        this.startOfEvent = LocalDateTime.parse(startOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        this.endOfEvent = LocalDateTime.parse(endOfEvent, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
        if (done.equals("1")) {super.setDone();}
    }

    public String getStartOfEvent() {
        return startOfEvent.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    public String getEndOfEvent() {
        return endOfEvent.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm'hrs'"));
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startOfEvent.format(DateTimeFormatter.ofPattern("d MMMM yyyy ha"))
                + " to: " + endOfEvent.format(DateTimeFormatter.ofPattern("d MMMM yyyy ha")) + ")";
    }
}