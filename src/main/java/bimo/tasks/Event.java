package bimo.tasks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;


    public Event(String details, LocalDate startDate, LocalDate endDate) {
        super(details);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Format start and end date to write into file
     * @return Returns start and end time as text
     */
    public String getDatesAsText() {
        return "|" + this.startDate.toString() + "/" + this.endDate.toString();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
