package BobChatBot.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{

    private final LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadlines(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
    }
    public Deadlines(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String export() {
        return String.format("deadline %s /by %s%s", super.export(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.deadlineTime != null ? " " + this.deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s%s)","D", super.toString(),
                this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.deadlineTime != null ? " " + this.deadlineTime : "");
    }
}
