import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDate startEvent;
    private final LocalDate endEvent;
    public Event(String taskName) {
        super(taskName.split("/from")[0]);
        this.startEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[0].trim());
        this.endEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[1].trim());
    }

    public Event(String taskName, boolean isCompleted) {
        super(taskName.split("/from")[0], isCompleted);
        this.startEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[0].trim());
        this.endEvent = LocalDate.parse(taskName.split("/from ")[1].split("/to")[1].trim());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + this.startEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.endEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String writeToFile() {
        return "E " + (super.isCompleted() ? "0" : "1") + " "
                + this.getTaskName() + "/from " + this.startEvent + " /to " + this.endEvent;
    }

    public LocalDate getStartEvent() {
        return this.startEvent;
    }

    public LocalDate getEndEvent() {
        return this.endEvent;
    }
}
