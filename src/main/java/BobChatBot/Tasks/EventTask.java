package BobChatBot.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{

    private final LocalDate startDate;
    private final LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public EventTask(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventTask(String taskName, LocalDate startDate, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public EventTask(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
    }

    public EventTask(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String export() {
        return String.format("event %s /from %s%s /to %s%s", super.export(),
                this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.startTime != null ? " " + this.startTime.format(DateTimeFormatter.ofPattern("HHmm")) : "",
                this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.endTime != null ? " " + this.endTime.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s%s to: %s%s)","E", super.toString(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.startTime != null ? " " + this.startTime : "",
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime != null ? " " + this.endTime : "");
    }
}
