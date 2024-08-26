import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{

    private LocalDateTime time;

    Deadlines(String content, String by) {
        super(content);
        this.time = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public LocalDateTime endTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + ")";
    }
}
