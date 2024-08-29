package Milo.TaskObj;
import java.time.LocalDate;

public class Events extends Task {

    public final LocalDate fromDate;

    public final LocalDate toDate;

    public Events(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.fromDate = from;
        this.toDate = to;
    }

    public Events(String desc, LocalDate from, LocalDate to, Boolean isCompleted) {
        super(desc, isCompleted);
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    @Override
    public String toTextString() {
        return "D" + super.toTextString() + " | " + fromDate + " | " + toDate;
    }
}
