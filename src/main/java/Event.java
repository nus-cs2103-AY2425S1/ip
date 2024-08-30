import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime endDate, startDate;
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
        this.startDate = startDate;
    }
    @Override
    public String listedString(){ return super.listedString() + " [from: " + super.localDateTimeToString(this.startDate) +
            " | to: " + super.localDateTimeToString(this.endDate) + "]"; }

    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate(){ return endDate; }
}
