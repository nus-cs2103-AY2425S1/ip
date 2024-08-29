package evelyn.task;

import java.time.LocalDate;

public class Event extends Task{
    private LocalDate startDate;
    private String startTime = null;
    private LocalDate endDate;
    private String endTime = null;

    public Event(String description, String start, String end, Boolean isMaked) {
        super(description, isMaked);

        if (start.contains(" ")) {
            String[] parts = start.split(" ");
            this.startDate = LocalDate.parse(parts[0]);
            this.startTime = parts[1];
        } else {
            this.startDate = LocalDate.parse(start);
        }

        if (end.contains(" ")) {
            String[] parts = start.split(" ");
            this.endDate = LocalDate.parse(parts[0]);
            this.endTime = parts[1];
        } else {
            this.endDate = LocalDate.parse(end);
        }
    };

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate +
                (this.startTime == null ? "": " " + this.startTime) +
                " to: " + this.endDate + (this.endTime == null ? "" : " " + this.endTime) + ")";
    }
}
