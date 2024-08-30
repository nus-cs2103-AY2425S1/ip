package task;
import exception.InputFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private final LocalDateTime byDate;
    public Deadline(String description, LocalDateTime byDate) throws InputFormatException{
        super(description);
        this.byDate = byDate;
    }

    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D | %s | %s", super.toFileFormatString(), byDate.format(formatter));
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:ma");
        return String.format("[D] %s (by: %s)\n", super.toString(), byDate.format(formatter));
    }



    public static boolean matchDeadline(String s) {
        return s.startsWith("deadline");
    }
}
