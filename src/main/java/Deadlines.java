import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Task {
    Parser parser = new Parser();
    private LocalDate endDate;
    private LocalTime endTime;
    public Deadlines(String description, String date) {
        super(description, TaskType.DEADLINE);
        String[] splitDate = date.split(" ");
        this.endDate = parser.convertDate(splitDate[0]);
        this.endTime = parser.convertTime(splitDate[1]);
    }

    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "D | " + checkMark + " | " + this.description + " | " +
                this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " +
                this.endTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() +
                " (by: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyy")) + " " +
                this.endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
