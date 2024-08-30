import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate date;
    public DeadlineTask(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String printTask() {
        String output = "[D]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description +
                " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public String writeTask() {
        String output = "D | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + " | " + date.toString() + "\n";
    }
}
