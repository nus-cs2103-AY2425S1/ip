import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    public Events(String description, String start, String end) {
        super(description, TaskType.EVENT);
        String[] splitStart = start.split(" ");
        String[] splitEnd = end.split(" ");
        this.startDate = Weeny.convertDate(splitStart[0]);
        this.endDate = Weeny.convertDate(splitEnd[0]);
        this.startTime = Weeny.convertTime(splitStart[1]);
        this.endTime = Weeny.convertTime(splitEnd[1]);
    }

    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "E | " + checkMark + " | " + this.description + " | " +
                this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " +
                this.startTime.format(DateTimeFormatter.ofPattern("HHmm")) + "-" +
                this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " +
                this.endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (from: " +
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + " " +
                this.startTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to: " +
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + " " +
                this.endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
